package omni.encyclopedia.actions.generic;

import omni.encyclopedia.Entity;
import omni.flow.Action;

public abstract class EntityToEntityAction extends Action {
    protected Entity source;
    protected Entity destination;

    public EntityToEntityAction(Entity source, Entity destination) {
        this.source = source;
        this.destination = destination;
    }
}
