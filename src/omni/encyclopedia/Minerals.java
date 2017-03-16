package omni.encyclopedia;

import bwapi.Player;
import omni.flow.Resource;

public class Minerals implements Resource {
    private Player self;
    private int minerals;

    public void setSelf(Player self) {
        this.self = self;
    }

    public Player getSelf() {
        return self;
    }

    public void update() {
        minerals = self.minerals();
    }

    public int getMineralCount() {
        return minerals;
    }

    @Override
    public String getID() {
        return "Minerals";
    }
}
