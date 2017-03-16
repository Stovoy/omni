package omni.encyclopedia.pipelines.worker;

import bwapi.UnitType;
import omni.encyclopedia.Entity;
import omni.encyclopedia.map.OmniMap;
import omni.encyclopedia.actions.TrainAction;
import omni.encyclopedia.generic.Producer;
import omni.encyclopedia.self.Self;
import omni.flow.Pipeline;
import omni.flow.Potential;
import omni.flow.scoring.Score;

import java.util.ArrayList;
import java.util.List;

public class BuildZerglingPipeline extends Pipeline {
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

        if (self.getMinerals().getCount() >= 50) {
            for (Producer producer : producers) {
                potentials.add(new Potential(Score.max(),
                        new TrainAction(producer, UnitType.Zerg_Zergling),
                        producer));
            }
        }

        return potentials;
    }
}
