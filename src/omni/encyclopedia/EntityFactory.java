package omni.encyclopedia;

import bwapi.Unit;
import bwapi.UnitType;

import java.util.HashMap;

public class EntityFactory {
    private HashMap<UnitType, Class<? extends Entity>> unitTypeToEntity;

    public EntityFactory(HashMap<UnitType, Class<? extends Entity>> unitTypeToEntity) {
        this.unitTypeToEntity = unitTypeToEntity;
    }

    public Entity newEntityFromUnit(Unit unit) {
        try {
            UnitType unitType = unit.getType();
            if (unitTypeToEntity.containsKey(unitType)) {
                Entity entity = unitTypeToEntity.get(unit.getType()).newInstance();
                entity.setUnit(unit);
                return entity;
            } else {
                // System.out.println(
                //      String.format("Unknown unit: %s", unitType));
                return null;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
