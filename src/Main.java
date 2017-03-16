import bwapi.*;
import bwapi.Flag.Enum;
import bwta.BWTA;
import omni.encyclopedia.Entity;
import omni.encyclopedia.Mapping;
import omni.encyclopedia.EntityFactory;
import omni.encyclopedia.Minerals;
import omni.flow.Flow;

import java.io.IOException;
import java.lang.Error;

public class Main extends DefaultBWListener {
    private Mirror mirror = new Mirror();
    private Game game;

    private Flow flow;
    private EntityFactory entityFactory;

    private void run() {
        runStarcraftWrapper();

        mirror.getModule().setEventListener(this);
        mirror.startGame();
    }

    private void runStarcraftWrapper() {
        try {
            Runtime.getRuntime().exec("wrapper.cmd");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onStart() {
        game = mirror.getGame();
        game.setLocalSpeed(30);
        game.enableFlag(Enum.UserInput.getValue());

        Minerals minerals = new Minerals();
        // TODO: Should I do this in the constructor?
        minerals.setSelf(game.self());

        flow = new Flow(minerals);
        entityFactory = new EntityFactory(Mapping.getUnitTypeEntityMap());

        // Use BWTA to analyze map. TODO: Switch to OmniMap.
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");
    }

    private int frame = 0;
    @Override
    public void onFrame() {
        long last = System.currentTimeMillis();

        try {
            flow.execute();
        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            long now = System.currentTimeMillis();
            System.out.format("%d: Took %d ms.\n", frame++, (now - last));
        }
    }

    @Override
    public void onUnitCreate(Unit unit) {
        try {
            Entity entity = entityFactory.newEntityFromUnit(unit);
            if (entity != null) {
                flow.addEntity(entity);
            }
        } catch (Error | Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUnitMorph(Unit unit) {
        Entity entity = flow.getEntityByUnit(unit);
        if (entity != null) {
            flow.removeEntity(entity);
        }
        onUnitCreate(unit);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}