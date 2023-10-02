package com.livelyspark.ludumdare54.systems.collisions;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.player.PersistentProjectileComponent;
import com.livelyspark.ludumdare54.components.player.PlayerProjectileComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.utility.HealthHelper;

import java.util.ArrayList;


public class PlayerBulletHitsEnemySystem extends EntitySystem {

    //private final Sound playerBulletHitsEnemySound;
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);

    private ComponentMapper<PlayerProjectileComponent> pm = ComponentMapper.getFor(PlayerProjectileComponent.class);
    private ComponentMapper<PersistentProjectileComponent> ppm = ComponentMapper.getFor(PersistentProjectileComponent.class);

    private ImmutableArray<Entity> enemyEntities;
    private ImmutableArray<Entity> playerBulletEntities;

    public PlayerBulletHitsEnemySystem() {
        //playerBulletHitsEnemySound = assetManager.get("sounds/hit.ogg", Sound.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        enemyEntities = engine.getEntitiesFor(Family.all(EnemyComponent.class, BoundingRectangleComponent.class, HealthComponent.class).get());
        playerBulletEntities = engine.getEntitiesFor(Family.all(PlayerProjectileComponent.class, BoundingRectangleComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();

        for (Entity e : enemyEntities) {

            BoundingRectangleComponent er = rm.get(e);

            for (Entity p : playerBulletEntities) {

                BoundingRectangleComponent pr = rm.get(p);

                if (pr.rectangle.overlaps(er.rectangle)) {

                    PlayerProjectileComponent pc = pm.get(p);
                    PersistentProjectileComponent ppc = ppm.get(p);
                    HealthComponent h = hm.get(e);

                    if(ppc!=null)
                    {
                        HealthHelper.ApplyDamage(h, pc.damage * deltaTime);
                        continue;
                    }

                    HealthHelper.ApplyDamage(h, pc.damage);
                    destroyed.add(p);
                }
            }
        }

        for(Entity e : destroyed)
        {
            getEngine().addEntity(new Entity().add(new SoundComponent(SoundKeys.Hit)));
            getEngine().removeEntity(e);
        }

    }

}
