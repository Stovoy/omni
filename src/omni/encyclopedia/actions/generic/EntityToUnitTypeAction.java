package omni.encyclopedia.actions.generic;

import bwapi.UnitType;
import omni.encyclopedia.Entity;
import omni.flow.Action;

public abstract class EntityToUnitTypeAction extends Action {
    protected Entity entity;
    protected UnitType targetType;

    public EntityToUnitTypeAction(Entity entity, UnitType targetType) {
        this.entity = entity;
        this.targetType = targetType;
    }
}
