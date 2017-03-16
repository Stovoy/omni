package omni.encyclopedia;

import bwapi.Position;
import bwapi.Unit;
import bwapi.UnitType;
import omni.flow.Resource;

public abstract class Entity implements Resource {
    protected Unit unit;

    private String unitType;

    public void setUnit(Unit unit) {
        this.unit = unit;
        unitType = getUnitTypeString();
    }

    public Unit getUnit() {
        return unit;
    }

    public UnitType getUnitType() {
        return Mapping.getUnitTypeForEntity(getClass());
    }

    public void update() {}

    @Override
    public String getID() {
        return "Entity " + unit.getID();
    }

    public String getUnitTypeString() {
        return unit.getType().toString().
                replaceAll("_", "").
                replaceAll("Zerg|Terran|Protoss|Resource", "");
    }
}
