package com.livelyspark.ludumdare54.systems.gamestages;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.livelyspark.ludumdare54.components.DebugLabelComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;

import java.util.ArrayList;
import java.util.Comparator;


// Spawning stuff goes here
public class GameStage01System extends EntitySystem {

    float stageTime = 0.0f;
    private ArrayList<IGameStageEvent> events = new ArrayList<IGameStageEvent>();

    public GameStage01System()
    {

    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

         Entity player = new Entity()
                .add(new TransformComponent(100, 100, 16, 16, 0.0f))
                .add(new VelocityComponent())
                .add(new PlayerComponent())
                .add(new DebugLabelComponent("Player"))
                .add(new BoundingRectangleComponent());

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
