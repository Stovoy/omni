package omni.encyclopedia.generic;

import omni.encyclopedia.Entity;

public abstract class Worker extends Entity {
    public boolean isIdle() {
        return unit.isIdle();
    }

    @Override
    public String getClassType() {
        return "Worker";
    }
}
