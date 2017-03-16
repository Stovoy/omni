package omni.flow;

import omni.encyclopedia.Entity;
import omni.encyclopedia.map.OmniMap;
import omni.encyclopedia.self.Self;

import java.util.List;

public abstract class Pipeline {
    protected abstract List<Potential> getPotentials(List<Entity> entities, Self self, OmniMap map);
}
