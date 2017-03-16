package omni.encyclopedia.self;

import bwapi.Player;

public class Self {
    private Player self;
    private Minerals minerals;
    private Supply supply;

    public Self(Player self) {
        this.self = self;
        minerals = new Minerals();
        supply = new Supply();
    }

    public void update() {
        minerals.setCount(self.minerals());
        supply.setUsed(self.supplyUsed());
        supply.setTotal(self.supplyTotal());
    }

    public Minerals getMinerals() {
        return minerals;
    }

    public Supply getSupply() {
        return supply;
    }
}
