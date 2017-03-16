package omni.encyclopedia.actions;

import bwapi.UnitType;
import omni.encyclopedia.actions.generic.EntityToUnitTypeAction;
import omni.encyclopedia.generic.Producer;

public class TrainAction extends EntityToUnitTypeAction {
    public TrainAction(Producer production, UnitType toTrain) {
        super(production, toTrain);
    }

    @Override
    public void execute() {
        entity.getUnit().train(targetType);
    }
}
