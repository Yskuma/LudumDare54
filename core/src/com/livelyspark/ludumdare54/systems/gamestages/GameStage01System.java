package com.livelyspark.ludumdare54.systems.gamestages;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.livelyspark.ludumdare54.components.DebugLabelComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.enums.AtlasRegions;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock2;
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

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(AtlasRegions.Ship001), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        ShipBase ship = new BlockShip();
        ship.shipParts.add(new ShipPartFitted(new EnginePartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GeneratorPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new HullPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new ShieldPartBlock2(),0,0));

        Entity player = ship.ToEntity(100,100,0,true,atlas);

        /*
         Entity player = new Entity()
                .add(new TransformComponent(100, 100, tr.getRegionWidth() * 2,tr.getRegionHeight() * 2, 0.0f))
                .add(new VelocityComponent())
                .add(new PlayerComponent())
                .add(new DebugLabelComponent("Player"))
                .add(new BoundingRectangleComponent())
                .add(new AnimationComponent(anim));
        */

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
