package omni.encyclopedia.self;

import bwapi.Player;
import omni.flow.Resource;

// TODO: These need to be "aggregate" or "count-based" resources.
public class Supply implements Resource {
    private Player self;
    private int used;
    private int total;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String getID() {
        return "Supply";
    }
}
