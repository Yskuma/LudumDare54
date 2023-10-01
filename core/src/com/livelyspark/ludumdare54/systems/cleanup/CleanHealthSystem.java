package com.livelyspark.ludumdare54.systems.cleanup;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;
import com.livelyspark.ludumdare54.keys.SoundKeys;


import java.util.ArrayList;

public class CleanHealthSystem extends EntitySystem {

    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ImmutableArray<Entity> entities;

    public CleanHealthSystem(){}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(HealthComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();

        for (Entity e : entities) {
            HealthComponent hc = hm.get(e);

            if (hc.hullCurrent <= 0) {

                TransformComponent pos = tm.get(e);
                if(pos != null)
                {

                }
                destroyed.add(e);
            }
        }

        for (Entity e : destroyed) {
            getEngine().addEntity(new Entity().add(new SoundComponent(SoundKeys.Explosion)));
            getEngine().removeEntity(e);
        }

    }

}
