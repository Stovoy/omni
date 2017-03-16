package omni.encyclopedia;

import bwapi.UnitType;
import omni.encyclopedia.neutral.*;
import omni.encyclopedia.zerg.structure.Hatchery;
import omni.encyclopedia.zerg.structure.SpawningPool;
import omni.encyclopedia.zerg.unit.*;

import java.util.HashMap;

public class Mapping {
    private static HashMap<UnitType, Class<? extends Entity>> unitTypeEntityMap;
    private static HashMap<Class<? extends Entity>, UnitType> entityUnitTypeMap;

    public static HashMap<UnitType, Class<? extends Entity>> getUnitTypeEntityMap() {
        return unitTypeEntityMap;
    }

    public static UnitType getUnitTypeForEntity(Class<? extends Entity> entityClass) {
        return entityUnitTypeMap.get(entityClass);
    }

    static {
        try {
            unitTypeEntityMap = new HashMap<>();
            addProtossUnits();
            addProtossBuildings();
            addTerranUnits();
            addTerranBuildings();
            addZergUnits();
            addZergBuildings();
            addResources();

            // Create reverse mapping.
            entityUnitTypeMap = new HashMap<>();
            for (UnitType unitType : unitTypeEntityMap.keySet()) {
                entityUnitTypeMap.put(unitTypeEntityMap.get(unitType), unitType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addProtossUnits() {
        // unitTypeEntityMap.put(UnitType.Protoss_Arbiter, OmniUnitType.Type.Arbiter);
        // unitTypeEntityMap.put(UnitType.Protoss_Archon, OmniUnitType.Type.Archon);
        // unitTypeEntityMap.put(UnitType.Protoss_Carrier, OmniUnitType.Type.Carrier);
        // unitTypeEntityMap.put(UnitType.Protoss_Corsair, OmniUnitType.Type.Corsair);
        // unitTypeEntityMap.put(UnitType.Protoss_Dark_Archon, OmniUnitType.Type.DarkArchon);
        // unitTypeEntityMap.put(UnitType.Protoss_Dark_Templar, OmniUnitType.Type.DarkTemplar);
        // unitTypeEntityMap.put(UnitType.Protoss_Dragoon, OmniUnitType.Type.Dragoon);
        // unitTypeEntityMap.put(UnitType.Protoss_High_Templar, OmniUnitType.Type.HighTemplar);
        // unitTypeEntityMap.put(UnitType.Protoss_Interceptor, OmniUnitType.Type.Interceptor);
        // unitTypeEntityMap.put(UnitType.Protoss_Observer, OmniUnitType.Type.Observer);
        // unitTypeEntityMap.put(UnitType.Protoss_Probe, OmniUnitType.Type.Probe);
        // unitTypeEntityMap.put(UnitType.Protoss_Reaver, OmniUnitType.Type.Reaver);
        // unitTypeEntityMap.put(UnitType.Protoss_Scarab, OmniUnitType.Type.Scarab);
        // unitTypeEntityMap.put(UnitType.Protoss_Scout, OmniUnitType.Type.Scout);
        // unitTypeEntityMap.put(UnitType.Protoss_Shuttle, OmniUnitType.Type.Shuttle);
        // unitTypeEntityMap.put(UnitType.Protoss_Zealot, OmniUnitType.Type.Zealot);
    }

    private static void addProtossBuildings() {
        // unitTypeEntityMap.put(UnitType.Protoss_Arbiter_Tribunal, Type.ArbiterTribunal);
        // unitTypeEntityMap.put(UnitType.Protoss_Assimilator, Type.Assimilator);
        // unitTypeEntityMap.put(UnitType.Protoss_Citadel_of_Adun, Type.CitadelofAdun);
        // unitTypeEntityMap.put(UnitType.Protoss_Cybernetics_Core, Type.CyberneticsCore);
        // unitTypeEntityMap.put(UnitType.Protoss_Fleet_Beacon, Type.FleetBeacon);
        // unitTypeEntityMap.put(UnitType.Protoss_Forge, Type.Forge);
        // unitTypeEntityMap.put(UnitType.Protoss_Gateway, Type.Gateway);
        // unitTypeEntityMap.put(UnitType.Protoss_Nexus, Type.Nexus);
        // unitTypeEntityMap.put(UnitType.Protoss_Observatory, Type.Observatory);
        // unitTypeEntityMap.put(UnitType.Protoss_Photon_Cannon, Type.PhotonCannon);
        // unitTypeEntityMap.put(UnitType.Protoss_Pylon, Type.Pylon);
        // unitTypeEntityMap.put(UnitType.Protoss_Robotics_Facility, Type.RoboticsFacility);
        // unitTypeEntityMap.put(UnitType.Protoss_Robotics_Support_Bay, Type.RoboticsSupportBay);
        // unitTypeEntityMap.put(UnitType.Protoss_Shield_Battery, Type.ShieldBattery);
        // unitTypeEntityMap.put(UnitType.Protoss_Stargate, Type.Stargate);
        // unitTypeEntityMap.put(UnitType.Protoss_Templar_Archives, Type.TemplarArchives);
    }

    private static void addTerranUnits() {
        // unitTypeEntityMap.put(UnitType.Terran_Battlecruiser, OmniUnitType.Type.Battlecruiser);
        // unitTypeEntityMap.put(UnitType.Terran_Dropship, OmniUnitType.Type.Dropship);
        // unitTypeEntityMap.put(UnitType.Terran_Firebat, OmniUnitType.Type.Firebat);
        // unitTypeEntityMap.put(UnitType.Terran_Ghost, OmniUnitType.Type.Ghost);
        // unitTypeEntityMap.put(UnitType.Terran_Goliath, OmniUnitType.Type.Goliath);
        // unitTypeEntityMap.put(UnitType.Terran_Marine, OmniUnitType.Type.Marine);
        // unitTypeEntityMap.put(UnitType.Terran_Medic, OmniUnitType.Type.Medic);
        // unitTypeEntityMap.put(UnitType.Terran_Nuclear_Missile, OmniUnitType.Type.NuclearMissile);
        // unitTypeEntityMap.put(UnitType.Terran_SCV, OmniUnitType.Type.SCV);
        // unitTypeEntityMap.put(UnitType.Terran_Science_Vessel, OmniUnitType.Type.ScienceVessel);
        // unitTypeEntityMap.put(UnitType.Terran_Siege_Tank_Siege_Mode, OmniUnitType.Type.SiegeTankSiegeMode);
        // unitTypeEntityMap.put(UnitType.Terran_Siege_Tank_Tank_Mode, OmniUnitType.Type.SiegeTankTankMode);
        // unitTypeEntityMap.put(UnitType.Terran_Valkyrie, OmniUnitType.Type.Valkyrie);
        // unitTypeEntityMap.put(UnitType.Terran_Vulture, OmniUnitType.Type.Vulture);
        // unitTypeEntityMap.put(UnitType.Terran_Vulture_Spider_Mine, OmniUnitType.Type.SpiderMine);
        // unitTypeEntityMap.put(UnitType.Terran_Wraith, OmniUnitType.Type.Wraith);
    }

    private static void addTerranBuildings() {
        // unitTypeEntityMap.put(UnitType.Terran_Academy, Type.Academy);
        // unitTypeEntityMap.put(UnitType.Terran_Armory, Type.Armory);
        // unitTypeEntityMap.put(UnitType.Terran_Barracks, Type.Barracks);
        // unitTypeEntityMap.put(UnitType.Terran_Bunker, Type.Bunker);
        // unitTypeEntityMap.put(UnitType.Terran_Command_Center, Type.CommandCenter);
        // unitTypeEntityMap.put(UnitType.Terran_Engineering_Bay, Type.EngineeringBay);
        // unitTypeEntityMap.put(UnitType.Terran_Factory, Type.Factory);
        // unitTypeEntityMap.put(UnitType.Terran_Missile_Turret, Type.MissileTurret);
        // unitTypeEntityMap.put(UnitType.Terran_Refinery, Type.Refinery);
        // unitTypeEntityMap.put(UnitType.Terran_Science_Facility, Type.ScienceFacility);
        // unitTypeEntityMap.put(UnitType.Terran_Starport, Type.Starport);
        // unitTypeEntityMap.put(UnitType.Terran_Supply_Depot, Type.SupplyDepot);
        // unitTypeEntityMap.put(UnitType.Terran_Comsat_Station, Type.ComsatStation);
        // unitTypeEntityMap.put(UnitType.Terran_Control_Tower, Type.ControlTower);
        // unitTypeEntityMap.put(UnitType.Terran_Covert_Ops, Type.CovertOps);
        // unitTypeEntityMap.put(UnitType.Terran_Machine_Shop, Type.MachineShop);
        // unitTypeEntityMap.put(UnitType.Terran_Nuclear_Silo, Type.NuclearSilo);
        // unitTypeEntityMap.put(UnitType.Terran_Physics_Lab, Type.PhysicsLab);
    }

    private static void addZergUnits() {
        // unitTypeEntityMap.put(UnitType.Zerg_Broodling, OmniUnitType.Type.Broodling);
        // unitTypeEntityMap.put(UnitType.Zerg_Cocoon, OmniUnitType.Type.Cocoon);
        // unitTypeEntityMap.put(UnitType.Zerg_Defiler, OmniUnitType.Type.Defiler);
        // unitTypeEntityMap.put(UnitType.Zerg_Devourer, OmniUnitType.Type.Devourer);
        unitTypeEntityMap.put(UnitType.Zerg_Drone, Drone.class);
        // unitTypeEntityMap.put(UnitType.Zerg_Egg, OmniUnitType.Type.Egg);
        // unitTypeEntityMap.put(UnitType.Zerg_Guardian, OmniUnitType.Type.Guardian);
        // unitTypeEntityMap.put(UnitType.Zerg_Hydralisk, OmniUnitType.Type.Hydralisk);
        // unitTypeEntityMap.put(UnitType.Zerg_Infested_Terran, OmniUnitType.Type.InfestedTerran);
        unitTypeEntityMap.put(UnitType.Zerg_Larva, Larva.class);
        // unitTypeEntityMap.put(UnitType.Zerg_Lurker, OmniUnitType.Type.Lurker);
        // unitTypeEntityMap.put(UnitType.Zerg_Lurker_Egg, OmniUnitType.Type.LurkerEgg);
        // unitTypeEntityMap.put(UnitType.Zerg_Mutalisk, OmniUnitType.Type.Mutalisk);
        // unitTypeEntityMap.put(UnitType.Zerg_Overlord, OmniUnitType.Type.Overlord);
        // unitTypeEntityMap.put(UnitType.Zerg_Queen, OmniUnitType.Type.Queen);
        // unitTypeEntityMap.put(UnitType.Zerg_Scourge, OmniUnitType.Type.Scourge);
        // unitTypeEntityMap.put(UnitType.Zerg_Ultralisk, OmniUnitType.Type.Ultralisk);
        unitTypeEntityMap.put(UnitType.Zerg_Zergling, Zergling.class);
    }

    private static void addZergBuildings() {
        // unitTypeEntityMap.put(UnitType.Zerg_Creep_Colony, Type.CreepColony);
        // unitTypeEntityMap.put(UnitType.Zerg_Defiler_Mound, Type.DefilerMound);
        // unitTypeEntityMap.put(UnitType.Zerg_Evolution_Chamber, Type.EvolutionChamber);
        // unitTypeEntityMap.put(UnitType.Zerg_Extractor, Type.Extractor);
        // unitTypeEntityMap.put(UnitType.Zerg_Greater_Spire, Type.GreaterSpire);
        unitTypeEntityMap.put(UnitType.Zerg_Hatchery, Hatchery.class);
        // unitTypeEntityMap.put(UnitType.Zerg_Hive, Type.Hive);
        // unitTypeEntityMap.put(UnitType.Zerg_Hydralisk_Den, Type.HydraliskDen);
        // unitTypeEntityMap.put(UnitType.Zerg_Infested_Command_Center, Type.InfestedCommandCenter);
        // unitTypeEntityMap.put(UnitType.Zerg_Lair, Type.Lair);
        // unitTypeEntityMap.put(UnitType.Zerg_Nydus_Canal, Type.NydusCanal);
        // unitTypeEntityMap.put(UnitType.Zerg_Queens_Nest, Type.QueensNest);
        unitTypeEntityMap.put(UnitType.Zerg_Spawning_Pool, SpawningPool.class);
        // unitTypeEntityMap.put(UnitType.Zerg_Spire, Type.Spire);
        // unitTypeEntityMap.put(UnitType.Zerg_Spore_Colony, Type.SporeColony);
        // unitTypeEntityMap.put(UnitType.Zerg_Sunken_Colony, Type.SunkenColony);
        // unitTypeEntityMap.put(UnitType.Zerg_Ultralisk_Cavern, Type.UltraliskCavern);
    }

    private static void addResources() {
        unitTypeEntityMap.put(UnitType.Resource_Mineral_Field, MineralField.class);
        // unitTypeEntityMap.put(UnitType.Resource_Mineral_Field_Type_2, MineralFieldType2.class);
        // unitTypeEntityMap.put(UnitType.Resource_Mineral_Field_Type_3, MineralFieldType3.class);
        // unitTypeEntityMap.put(UnitType.Resource_Vespene_Geyser, VespeneGeyser.class);
    }

    private static void addSpells() {
        // unitTypeEntityMap.put(UnitType.Spell_Dark_Swarm, );
        // unitTypeEntityMap.put(UnitType.Spell_Disruption_Web, );
        // unitTypeEntityMap.put(UnitType.Spell_Scanner_Sweep, );
    }
}
