package omni.encyclopedia.generic;

import bwapi.Position;
import omni.encyclopedia.Entity;

public abstract class Attacker extends Entity {
    public void attackLocation(Position position) {
        unit.attack(position);
    }

    // TODO: No quueuing in code - should be continuous.
    public void queueAttackLocation(Position position) {
        unit.attack(position, true);
    }
}
