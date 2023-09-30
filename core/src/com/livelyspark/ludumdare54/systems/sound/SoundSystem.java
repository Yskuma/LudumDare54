package com.livelyspark.ludumdare54.systems.sound;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;

import java.util.ArrayList;

public class SoundSystem extends EntitySystem {

    private ComponentMapper<SoundComponent> sm = ComponentMapper.getFor(SoundComponent.class);
    private ImmutableArray<Entity> entities;

    public SoundSystem(){}

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
            SoundComponent sc = sm.get(e);
        }

        for (Entity e : entities) {
            getEngine().removeEntity(e);
        }

    }

}
