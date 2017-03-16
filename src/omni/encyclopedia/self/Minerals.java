package omni.encyclopedia.self;

import bwapi.Player;
import omni.flow.Resource;

// TODO: These need to be "aggregate" or "count-based" resources.
public class Minerals implements Resource {
    private Player self;
    private int count;

    protected void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String getID() {
        return "Minerals";
    }
}
