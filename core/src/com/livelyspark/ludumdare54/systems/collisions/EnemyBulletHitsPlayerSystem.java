package com.livelyspark.ludumdare54.systems.collisions;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.enemy.EnemyProjectileComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.player.PlayerProjectileComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.utility.HealthHelper;

import java.util.ArrayList;


public class EnemyBulletHitsPlayerSystem extends EntitySystem {

    //private final Sound enemyBulletHitsPlayerSound;
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);
    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);

    private ComponentMapper<EnemyProjectileComponent> pm = ComponentMapper.getFor(EnemyProjectileComponent.class);

    private ImmutableArray<Entity> enemyBulletEntities;
    private ImmutableArray<Entity> playerEntities;

    public EnemyBulletHitsPlayerSystem() {
        //enemyBulletHitsPlayerSound = assetManager.get("sounds/hit.ogg", Sound.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        enemyBulletEntities = engine.getEntitiesFor(Family.all(EnemyProjectileComponent.class, BoundingRectangleComponent.class).get());
        playerEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundingRectangleComponent.class, HealthComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        ArrayList<Entity> destroyed = new ArrayList<Entity>();

        for (Entity p : playerEntities) {

            BoundingRectangleComponent er = rm.get(p);

            for (Entity e : enemyBulletEntities) {

                BoundingRectangleComponent pr = rm.get(e);

                if (pr.rectangle.overlaps(er.rectangle)) {
                    EnemyProjectileComponent pc = pm.get(e);
                    HealthComponent h = hm.get(p);
                    HealthHelper.ApplyDamage(h, pc.damage);
                    destroyed.add(e);
                }
            }
        }

        for(Entity e : destroyed)
        {
            getEngine().addEntity(new Entity().add(new SoundComponent(SoundKeys.Hit)));
            //enemyBulletHitsPlayerSound.play(StaticConstants.sfxVolume);
            getEngine().removeEntity(e);
        }

    }

}
