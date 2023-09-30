package com.livelyspark.ludumdare54.systems.collisions;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.utility.HealthHelper;

import java.util.ArrayList;


public class PlayerHitsEnemySystem extends EntitySystem {

    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);

    private ImmutableArray<Entity> enemyEntities;
    private ImmutableArray<Entity> playerEntities;

    public PlayerHitsEnemySystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        enemyEntities = engine.getEntitiesFor(Family.all(EnemyComponent.class, BoundingRectangleComponent.class).get());
        playerEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundingRectangleComponent.class, HealthComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();

        for (Entity e : enemyEntities) {

            BoundingRectangleComponent er = rm.get(e);

            for (Entity p : playerEntities) {

                BoundingRectangleComponent pr = rm.get(p);

                if (pr.rectangle.overlaps(er.rectangle)) {

                    HealthComponent playerHealth = hm.get(p);
                    HealthComponent enemyHealth = hm.get(e);

                    HealthHelper.ApplyDamage(playerHealth, enemyHealth.hullMax * deltaTime);
                    HealthHelper.ApplyDamage(enemyHealth, playerHealth.hullMax * deltaTime);

                }
            }
        }

    }

}
