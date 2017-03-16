package omni.encyclopedia.pipelines.worker;

import bwapi.TilePosition;
import bwapi.UnitType;
import omni.encyclopedia.Entity;
import omni.encyclopedia.actions.BuildStructureAction;
import omni.encyclopedia.generic.Worker;
import omni.encyclopedia.map.OmniMap;
import omni.encyclopedia.self.Self;
import omni.encyclopedia.zerg.structure.Hatchery;
import omni.encyclopedia.zerg.structure.SpawningPool;
import omni.flow.Pipeline;
import omni.flow.Potential;
import omni.flow.scoring.Score;

import java.util.ArrayList;
import java.util.List;

public class BuildSpawningPoolPipeline extends Pipeline {
    @Override
    protected List<Potential> getPotentials(List<Entity> entities, Self self, OmniMap map) {
        List<Potential> potentials = new ArrayList<>();

        List<Worker> workers = new ArrayList<>();

        Hatchery hatchery = null;
        for (Entity entity : entities) {
            if (entity instanceof Worker) {
                Worker worker = (Worker) entity;
                workers.add(worker);
            } else if (entity instanceof SpawningPool) {
                // Already have a spawning pool.
                return potentials;
            } else if (entity instanceof Hatchery) {
                hatchery = (Hatchery) entity;
            }
        }

        if (workers.size() == 0) {
            // No workers?
            return potentials;
        }

        if (hatchery == null) {
            // No hatchery?
            return potentials;
        }

        TilePosition hatcheryPosition = hatchery.getUnit().getTilePosition();
        int hatcheryX = hatcheryPosition.getX();
        int hatcheryY = hatcheryPosition.getY();

        if (self.getMinerals().getCount() >= 200) {
            // TODO: Better selection based on distance to position, just going with 1st for now.
            // TODO: They all want to resource lock the "spawning pool" objective.
            Worker worker = workers.get(0);
            // TODO: Fix super hacky way of finding valid building location.
            // TODO: Fix not locking the worker.
            // (all potentials will execute and only the one that succeeds will run.
            potentials.add(new Potential(Score.max(),
                    new BuildStructureAction(worker, UnitType.Zerg_Spawning_Pool,
                            new TilePosition(hatcheryX - 5, hatcheryY))));
            potentials.add(new Potential(Score.max(),
                    new BuildStructureAction(worker, UnitType.Zerg_Spawning_Pool,
                            new TilePosition(hatcheryX + 5, hatcheryY))));
            potentials.add(new Potential(Score.max(),
                    new BuildStructureAction(worker, UnitType.Zerg_Spawning_Pool,
                            new TilePosition(hatcheryX, hatcheryY - 5))));
            potentials.add(new Potential(Score.max(),
                    new BuildStructureAction(worker, UnitType.Zerg_Spawning_Pool,
                            new TilePosition(hatcheryX, hatcheryY + 5))));
        }

        return potentials;
    }
}
