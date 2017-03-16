package omni.encyclopedia.pipelines.worker;

import bwapi.UnitType;
import omni.encyclopedia.Entity;
import omni.encyclopedia.actions.TrainAction;
import omni.encyclopedia.generic.Producer;
import omni.encyclopedia.map.OmniMap;
import omni.encyclopedia.self.Self;
import omni.flow.Pipeline;
import omni.flow.Potential;
import omni.flow.scoring.Score;

import java.util.ArrayList;
import java.util.List;

public class BuildOverlordPipeline extends Pipeline {
    @Override
    protected List<Potential> getPotentials(List<Entity> entities, Self self, OmniMap map) {
        List<Potential> potentials = new ArrayList<>();
        List<Producer> producers = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity instanceof Producer) {
                // TODO: Need to filter on those that can produce zerglings.
                Producer producer = (Producer) entity;
                if (producer.canProduce()) {
                    producers.add(producer);
                }
            }
        }

        if (self.getSupply().getUsed() == self.getSupply().getTotal() &&
                self.getMinerals().getCount() >= 100) {
            for (Producer producer : producers) {
                // TODO: We only want to build one overlord at a time, so lock on that "objective" (not always true).
                // TODO: Needs to know it is already building an overlord!
                // If it has overlord mid production and hits 100 mineral on a different frame, this will build another.
                potentials.add(new Potential(Score.max(),
                        new TrainAction(producer, UnitType.Zerg_Overlord),
                        producer));
            }
        }

        return potentials;
    }
}
