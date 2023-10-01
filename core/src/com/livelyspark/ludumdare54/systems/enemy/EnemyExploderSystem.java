package com.livelyspark.ludumdare54.systems.enemy;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ai.AiMoveAndHoldComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyExploderComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.utility.HealthHelper;

import java.util.ArrayList;

public class EnemyExploderSystem extends EntitySystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<EnemyExploderComponent> em = ComponentMapper.getFor(EnemyExploderComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);

    private ImmutableArray<Entity> exploderEntities;
    private ImmutableArray<Entity> playerEntities;

    public EnemyExploderSystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        exploderEntities = engine.getEntitiesFor(Family.all(EnemyExploderComponent.class, TransformComponent.class).get());
        playerEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class, TransformComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();

        for (Entity e : exploderEntities) {

            EnemyExploderComponent ee = em.get(e);
            TransformComponent et = tm.get(e);

            for (Entity p : playerEntities) {

                TransformComponent pt = tm.get(p);

                float distance = new Vector2(et.position).sub(pt.position).len();
                if ((distance < ee.range)) {
                    HealthComponent playerHealth = hm.get(p);
                    HealthHelper.ApplyDamage(playerHealth, ee.damage);
                    destroyed.add(e);
                }
            }
        }

        for (Entity e : destroyed) {
            getEngine().addEntity(new Entity().add(new SoundComponent(SoundKeys.Explosion)));
            getEngine().removeEntity(e);
        }

    }
}
