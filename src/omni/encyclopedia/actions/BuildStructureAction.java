package omni.encyclopedia.actions;

import bwapi.TilePosition;
import bwapi.UnitType;
import omni.encyclopedia.generic.Worker;
import omni.flow.Action;

public class BuildStructureAction extends Action {
    private Worker worker;
    private UnitType structureType;
    private TilePosition tilePosition;

    public BuildStructureAction(Worker worker, UnitType structureType, TilePosition tilePosition) {
        this.worker = worker;
        this.structureType = structureType;
        this.tilePosition = tilePosition;
    }

    @Override
    public void execute() {
        worker.getUnit().build(structureType, tilePosition);
    }
}
