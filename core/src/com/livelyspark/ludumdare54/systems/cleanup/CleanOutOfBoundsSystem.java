package com.livelyspark.ludumdare54.systems.cleanup;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;


import java.util.ArrayList;


public class CleanOutOfBoundsSystem extends EntitySystem {


    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    private boolean lastPressed = false;
    private ImmutableArray<Entity> entities;

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();


        for (Entity e : entities) {
            TransformComponent tc = tm.get(e);
            if (tc.position.x < -200 ||
                    tc.position.x > 1200 ||
                    tc.position.y < -200 ||
                    tc.position.y > 700) {
                destroyed.add(e);
            }
        }


        for (Entity e : destroyed) {
            getEngine().removeEntity(e);
        }

    }

}
