package omni.encyclopedia.actions;

import omni.encyclopedia.actions.generic.EntityToEntityAction;
import omni.encyclopedia.generic.Worker;
import omni.encyclopedia.neutral.MineralField;

public class GatherAction extends EntityToEntityAction {
    public GatherAction(Worker worker, MineralField resourceField) {
        super(worker, resourceField);
    }

    @Override
    public void execute() {
        System.out.println(
                String.format("%s gathering from %s", source.getID(), destination.getID()));
        source.getUnit().gather(destination.getUnit());
    }
}
