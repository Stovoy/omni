package omni.flow;

import bwapi.Player;
import bwapi.Unit;
import omni.encyclopedia.Entity;
import omni.encyclopedia.map.OmniMap;
import omni.encyclopedia.pipelines.worker.*;
import omni.encyclopedia.self.Minerals;
import omni.encyclopedia.self.Self;
import omni.flow.scoring.AggregateScore;

import java.util.*;

public class Flow {
    // TODO: Better way to represent all information.
    //  Entities + self + partial information about enemies + map info.
    private Self self;
    private OmniMap map;
    private List<Entity> entities;
    private List<Pipeline> pipelines;

    public Flow(Player self, OmniMap map) {
        this.self = new Self(self);
        this.map = map;

        entities = new ArrayList<>();

        pipelines = new ArrayList<>();
        pipelines.add(new AttackPipeline());
        pipelines.add(new BuildOverlordPipeline());
        pipelines.add(new BuildSpawningPoolPipeline());
        pipelines.add(new BuildZerglingPipeline());
        pipelines.add(new WorkerGatherPipeline());
        // pipelines.add(new BuildWorkerPipeline()); Who needs workers?
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Entity getEntityByUnit(Unit unit) {
        for (Entity entity : entities) {
            if (entity.getUnit() == unit) {
                return entity;
            }
        }
        return null;
    }

    public void execute() {
        self.update();
        for (Entity entity : entities) {
            entity.update();
        }

        // TODO: Parallelize these steps with map / reduce.
        List<Potential> potentials = new ArrayList<>();
        for (Pipeline pipeline : pipelines) {
            potentials.addAll(
                    pipeline.getPotentials(entities, self, map));
        }

        Map<String, List<Potential>> resourceKeyToPotentials = new HashMap<>();
        Map<String, Map<String, Resource>> resourceGroups = new HashMap<>();

        List<Potential> potentialsToUse = new ArrayList<>();
        for (Potential potential : potentials) {
            if (potential.getUtilizedResources().size() == 0) {
                potentialsToUse.add(potential);  // TODO: Do we ever really need this logic?
                continue;
            }

            Map<String, Resource> resourceGroup = new HashMap<>();
            for (Resource resource : potential.getUtilizedResources()) {
                String resourceKey = resource.getID();
                resourceGroup.put(resourceKey, resource);

                // Record source potential.
                if (!resourceKeyToPotentials.containsKey(resourceKey)) {
                    resourceKeyToPotentials.put(resourceKey, new ArrayList<>());
                }
                resourceKeyToPotentials.get(resourceKey).add(potential);

                // Put all related resources into this group.
                if (resourceGroups.containsKey(resourceKey)) {
                    for (Map.Entry<String, Resource> entry : resourceGroups.get(resourceKey).entrySet()) {
                        resourceGroup.put(entry.getKey(), entry.getValue());
                    }
                }
            }

            // Write each resource key -> resource group relation into the main map.
            // This effectively combine all related resource groups by overwriting.
            for (String resourceKey : resourceGroup.keySet()) {
                resourceGroups.put(resourceKey, resourceGroup);
            }
        }

        for (Map<String, Resource> resourceGroup : resourceGroups.values()) {
            if (resourceGroup.size() == 1) {
                // Only one resource that these contest over.
                List<Potential> relatedPotentials = new ArrayList<>();
                for (Resource resource : resourceGroup.values()) {
                    for (Potential potential : resourceKeyToPotentials.get(resource.getID())) {
                        relatedPotentials.add(potential);
                    }
                }
                // Use the best related potential.
                Collections.sort(relatedPotentials);
                potentialsToUse.add(relatedPotentials.get(relatedPotentials.size() - 1));
            } else {
                Set<Potential> relatedPotentials = new HashSet<>();
                for (Resource resource : resourceGroup.values()) {
                    for (Potential potential : resourceKeyToPotentials.get(resource.getID())) {
                        relatedPotentials.add(potential);
                    }
                }
                List<List<Potential>> potentialPermutations = new ArrayList<>();

                // TODO: Do / While
                // TODO: Compute permutations iterably rather than up front.
                List<Potential> firstPermutation = new ArrayList<>(relatedPotentials);
                potentialPermutations.add(firstPermutation);

                int swapIndex = 0;
                if (relatedPotentials.size() > 1) {
                    List<Potential> previousPermutation = firstPermutation;
                    while (true) {
                        List<Potential> permutation = new ArrayList<>(previousPermutation);
                        Potential temp = permutation.get(swapIndex);
                        permutation.set(swapIndex, permutation.get(swapIndex + 1));
                        permutation.set(swapIndex + 1, temp);
                        swapIndex++;
                        if (swapIndex == permutation.size() - 1) {
                            swapIndex = 0;
                        }

                        if (permutation.equals(firstPermutation)) {
                            // TODO: This is also horribly slow,
                            break;
                        }
                        potentialPermutations.add(permutation);
                        previousPermutation = permutation;
                    }
                }

                int bestScore = 0;
                List<Potential> bestPotentials = null;
                for (List<Potential> permutation : potentialPermutations) {
                    Set<String> utilizedResources = new HashSet<>();

                    List<Potential> utilizedPotentials = new ArrayList<>();
                    AggregateScore aggregate = new AggregateScore();
                    for (Potential potential : permutation) {
                        for (Resource utilizedResource : potential.getUtilizedResources()) {
                            if (utilizedResources.contains(utilizedResource.getID())) {
                                // Already used - can't act on this potential.
                                continue;
                            }
                            aggregate.add(potential.getScore());
                            utilizedPotentials.add(potential);
                            utilizedResources.add(utilizedResource.getID());
                        }
                    }
                    if (aggregate.get() > bestScore) {
                        bestScore = aggregate.get();
                        bestPotentials = utilizedPotentials;
                    }
                }
                if (bestPotentials == null) {
                    throw new AssertionError();
                }
                potentialsToUse.addAll(bestPotentials);
            }
        }

        for (Potential potential : potentialsToUse) {
            potential.getAction().execute();
        }
    }
}
