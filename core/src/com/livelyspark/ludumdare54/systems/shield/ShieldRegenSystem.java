package com.livelyspark.ludumdare54.systems.shield;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;

import java.util.ArrayList;

public class ShieldRegenSystem extends EntitySystem {

    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    private ImmutableArray<Entity> entities;
    public ShieldRegenSystem(){}

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

            hc.shieldCurrent = Math.min(hc.shieldMax, hc.shieldCurrent + (deltaTime * hc.shieldRegen));
        }

        for (Entity e : destroyed) {
            getEngine().removeEntity(e);
        }

    }

}
