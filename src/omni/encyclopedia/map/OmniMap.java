package omni.encyclopedia.map;

import java.util.List;

public class OmniMap {
    private List<Base> bases;

    public OmniMap(List<Base> bases) {
        this.bases = bases;
    }

    public List<Base> getBases() {
        return bases;
    }
}
