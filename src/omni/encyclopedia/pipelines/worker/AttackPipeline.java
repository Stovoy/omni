package omni.encyclopedia.pipelines.worker;

import bwapi.Position;
import omni.encyclopedia.Entity;
import omni.encyclopedia.actions.AttackAction;
import omni.encyclopedia.generic.Attacker;
import omni.encyclopedia.map.Base;
import omni.encyclopedia.map.OmniMap;
import omni.encyclopedia.self.Self;
import omni.encyclopedia.zerg.structure.Hatchery;
import omni.flow.Pipeline;
import omni.flow.Potential;
import omni.flow.scoring.Score;

import java.util.ArrayList;
import java.util.List;

public class AttackPipeline extends Pipeline {
    @Override
    protected List<Potential> getPotentials(List<Entity> entities, Self self, OmniMap map) {
        List<Potential> potentials = new ArrayList<>();
        List<Attacker> attackers = new ArrayList<>();
        Hatchery hatchery = null;
        for (Entity entity : entities) {
            if (entity instanceof Attacker) {
                Attacker attacker = (Attacker) entity;
                if (!attacker.getUnit().isIdle()) {
                    continue;
                }
                attackers.add(attacker);
            } else if (entity instanceof Hatchery) {
                hatchery = (Hatchery) entity;
            }
        }

        if (hatchery == null) {
            // No hatchery?
            return potentials;
        }

        for (Attacker attacker : attackers) {
            List<Position> enemyBasePositions = new ArrayList<>();

            // TODO: Better way to store / figure out which one is my base in OmniMap data.
            Position closestBasePosition = null;
            int closestDistance = Integer.MAX_VALUE;

            for (Base base : map.getBases()) {
                Position basePosition = base.getPosition();
                enemyBasePositions.add(basePosition);

                int distance = hatchery.getUnit().getDistance(basePosition);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestBasePosition = basePosition;
                }
            }

            if (closestBasePosition != null) {
                enemyBasePositions.remove(closestBasePosition);
            }

            potentials.add(new Potential(Score.max(),
                    new AttackAction(attacker, enemyBasePositions),
                    attacker));
        }

        return potentials;
    }
}
