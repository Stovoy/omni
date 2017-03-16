package omni.encyclopedia.zerg.unit;

import omni.encyclopedia.generic.Producer;

public class Larva extends Producer {
    @Override
    public boolean canProduce() {
        // TODO: Is a larva in a training state, or is it an egg?
        return !unit.isTraining();
    }
}
