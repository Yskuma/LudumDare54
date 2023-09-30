package com.livelyspark.ludumdare54.systems.cleanup;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.ships.HullComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;


import java.util.ArrayList;

public class CleanHealthSystem extends EntitySystem {

    private ComponentMapper<HullComponent> hm = ComponentMapper.getFor(HullComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ImmutableArray<Entity> entities;

    public CleanHealthSystem(){}

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(HullComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();

        for (Entity e : entities) {
            HullComponent hc = hm.get(e);

            if (hc.hullCurrent <= 0) {

                TransformComponent pos = tm.get(e);

                if(pos != null)
                {

                }

                destroyed.add(e);
            }
        }

        for (Entity e : destroyed) {
            getEngine().removeEntity(e);
        }

    }

}
