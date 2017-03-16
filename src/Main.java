import bwapi.*;
import bwapi.Flag.Enum;
import bwta.BWTA;
import bwta.BaseLocation;
import omni.encyclopedia.Entity;
import omni.encyclopedia.Mapping;
import omni.encyclopedia.EntityFactory;
import omni.encyclopedia.map.Base;
import omni.encyclopedia.map.OmniMap;
import omni.flow.Flow;

import java.io.IOException;
import java.lang.Error;
import java.util.ArrayList;
import java.util.List;

public class Main extends DefaultBWListener {
    private Mirror mirror = new Mirror();
    private Game game;

    private Flow flow;
    private EntityFactory entityFactory;

    private boolean dev = false;

    public Main(boolean dev) {
        this.dev = dev;
    }

    private void run() {
        if (dev) {
            runStarcraftWrapper();
        }

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
        if (dev) {
            game.setLocalSpeed(30);
            game.enableFlag(Enum.UserInput.getValue());
        }

        // Use BWTA to analyze map. TODO: Switch to OmniMap.
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");

        List<Base> bases = new ArrayList<>();
        for (BaseLocation baseLocation : BWTA.getStartLocations()) {
            bases.add(new Base(
                    baseLocation.getRegion().getPolygon().getCenter()));
        }

        OmniMap map = new OmniMap(bases);

        flow = new Flow(game.self(), map);
        entityFactory = new EntityFactory(Mapping.getUnitTypeEntityMap());
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
            if (dev) {
                System.out.format("%d: Took %d ms.\n", frame++, (now - last));
            }
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
        boolean dev = false;
        if (args.length == 1 && args[0].equals("--dev")) {
            dev = true;
        }
        new Main(dev).run();
    }
}