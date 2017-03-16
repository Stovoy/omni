package omni.flow;

import omni.encyclopedia.Entity;
import omni.encyclopedia.Minerals;

import java.util.List;

public abstract class Pipeline {
    protected abstract List<Potential> getPotentials(List<Entity> entities, Minerals mineralCount);
}
