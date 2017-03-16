package omni.encyclopedia.generic;

import omni.encyclopedia.Entity;

public abstract class Producer extends Entity {
    public boolean canProduce() {
        return !unit.isTraining();
    }
}
