package omni.encyclopedia.pipelines.worker;

import bwapi.UnitType;
import omni.encyclopedia.Entity;
import omni.encyclopedia.Minerals;
import omni.encyclopedia.actions.GatherAction;
import omni.encyclopedia.generic.Worker;
import omni.encyclopedia.neutral.MineralField;
import omni.flow.*;
import omni.flow.scoring.DistanceScore;

import java.util.ArrayList;
import java.util.List;

public class WorkerGatherPipeline extends Pipeline {
    @Override
    protected List<Potential> getPotentials(List<Entity> entities, Minerals minerals) {
        List<Worker> workers = new ArrayList<>();
        List<Worker> gatheringWorkers = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getClassType().equals("Worker")) {
                Worker worker = (Worker) entity;
                if (worker.isIdle()) {
                    // TODO: Eventually need to use non-idle workers too (like moving workers on oversatured mineral patches.)
                    workers.add(worker);
                } else if (worker.getUnit().isGatheringMinerals()) {
                    gatheringWorkers.add(worker);
                }
            }
        }

        List<MineralField> mineralFields = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getUnitType() == UnitType.Resource_Mineral_Field) {
                MineralField mineralField = (MineralField) entity;

                // TODO: Improve this.
                int amountGathering = 0;
                for (Worker worker : gatheringWorkers) {
                    if (worker.getUnit().getTarget() == null) {
                        System.out.println(String.format("Null target for %s", worker.getID()));
                        continue;
                    }
                    if (worker.getUnit().getTarget().equals(mineralField.getUnit())) {
                        amountGathering++;
                    }
                }
                mineralField.setAmountGathering(amountGathering);

                mineralFields.add(mineralField);
            }
        }

        List<Potential> potentials = new ArrayList<>();
        for (Worker worker : workers) {
            for (MineralField mineralField : mineralFields) {
                int distance = worker.getUnit().getDistance(mineralField.getUnit());

                // Prune far away mineral fields.
                // TODO: Need to handle this pruning better in the future.
                final int maxDistance = 100000;
                if (mineralField.getAmountGathering() >= 3) {
                    continue;
                }
                // TODO: Improve this.
                if (mineralField.getAmountGathering() >= 2) {
                    distance += 1000;
                } else if (mineralField.getAmountGathering() >= 1) {
                    distance += 5000;
                }
                DistanceScore distanceScore = new DistanceScore(distance, maxDistance);
                if (distanceScore.get() == 0) {
                    continue;
                }
                System.out.println(
                        String.format("Adding gather potential for worker %s, field %s",
                            worker.getID(), mineralField.getID()));
                Potential gatherPotential = new Potential(distanceScore,
                        new GatherAction(worker, mineralField));
                gatherPotential.addUtilizedResource(worker);

                potentials.add(gatherPotential);
            }
        }

        return potentials;
    }
}
