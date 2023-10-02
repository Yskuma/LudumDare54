package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.livelyspark.ludumdare54.GlobalGameState;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

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

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(AtlasKeys.Ship_Player_001), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        Entity player = GlobalGameState.ship.ToEntity(100,100,0,true,atlas);
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
