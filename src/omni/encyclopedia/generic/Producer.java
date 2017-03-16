package omni.encyclopedia.generic;

import omni.encyclopedia.Entity;

public abstract class Producer extends Entity {
    public abstract boolean canProduce();

    @Override
    public String getClassType() {
        return "Producer";
    }
}
