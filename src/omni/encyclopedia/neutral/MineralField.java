package omni.encyclopedia.neutral;

import omni.encyclopedia.Entity;

public class MineralField extends Entity {
    private int amount;
    private int amountGathering;

    @Override
    public void update() {
        super.update();
        amount = unit.getResources();
    }

    public int getAmountGathering() {
        return amountGathering;
    }

    public void setAmountGathering(int amountGathering) {
        this.amountGathering = amountGathering;
    }

    @Override
    public String getID() {
        return "Mineral Field " + unit.getID();
    }
}
