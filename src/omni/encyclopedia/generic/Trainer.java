package omni.encyclopedia.generic;

import omni.encyclopedia.Entity;

public abstract class Trainer extends Entity {
    public void Train(Entity target) {
        unit.train(target.getUnitType());
    }
}
