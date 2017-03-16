package omni.encyclopedia;

import bwapi.Position;
import bwapi.Unit;
import bwapi.UnitType;
import omni.flow.Resource;

public abstract class Entity implements Resource {
    protected Unit unit;

    private Position position;
    private String unitType;
    private String classType;

    public void setUnit(Unit unit) {
        this.unit = unit;

        position = unit.getPosition();
        unitType = getUnitTypeString();
        classType = getClassType();
    }

    public Unit getUnit() {
        return unit;
    }

    public UnitType getUnitType() {
        return Mapping.getUnitTypeForEntity(getClass());
    }

    public void update() {
        // TODO: Don't do this for stationary entities.
        position = unit.getPosition();
    }

    @Override
    public String getID() {
        return "Entity " + unit.getID();
    }

    public String getUnitTypeString() {
        return unit.getType().toString().
                replaceAll("_", "").
                replaceAll("Zerg|Terran|Protoss|Resource", "");
    }

    public abstract String getClassType();
}
