package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.rendering.ShapeComponent;
import com.livelyspark.ludumdare54.components.rendering.TextComponent;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.FontKeys;
import com.livelyspark.ludumdare54.enums.Shapes;
import com.livelyspark.ludumdare54.prefab.EnemyFactory;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;

import java.util.ArrayList;
import java.util.Comparator;


// Spawning stuff goes here
public class GameStage01System extends EntitySystem {

    private final TextureAtlas atlas;
    float stageTime = 0.0f;
    private ArrayList<IGameStageEvent> events = new ArrayList<IGameStageEvent>();

    public GameStage01System(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(AtlasKeys.Ship001), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        Entity player = PlayerShipTemp().ToEntity(100,100,0,true,atlas);
        getEngine().addEntity(player);

        events.sort(new Comparator<IGameStageEvent>() {
            @Override
            public int compare(IGameStageEvent o1, IGameStageEvent o2) {
                if(o1.getEventTime() < o2.getEventTime()) return -1;
                if(o1.getEventTime() > o2.getEventTime()) return 1;
                return 0;
            }
        });
    }

    private ShipBase PlayerShipTemp()
    {
        ShipBase ship = new BlockShip();
        ship.shipParts.add(new ShipPartFitted(new EnginePartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GeneratorPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new HullPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new ShieldPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GunPartBlock2(),0,0));
        return ship;
    }

    @Override
    public void update (float deltaTime) {
        stageTime += deltaTime;

        if(events.isEmpty())
        {
            return;
        }

        IGameStageEvent nextEvent = events.get(0);

        if(stageTime > nextEvent.getEventTime())
        {
            nextEvent.event();
            events.remove(0);
        }
    }


}
