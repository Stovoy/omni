package omni.encyclopedia.actions;

import bwapi.Position;
import omni.encyclopedia.generic.Attacker;
import omni.flow.Action;

import java.util.List;

public class AttackAction extends Action {
    private Attacker attacker;
    private List<Position> positions;

    public AttackAction(Attacker attacker, List<Position> positions) {
        this.attacker = attacker;
        this.positions = positions;
    }

    @Override
    public void execute() {
        for (Position position : positions) {
            // TODO: Remove all queueing.
            attacker.queueAttackLocation(position);
        }
    }
}
