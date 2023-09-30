package com.livelyspark.ludumdare54.systems.collisions;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.enemy.EnemyProjectileComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.HullComponent;

import java.util.ArrayList;


public class EnemyBulletHitsPlayerSystem extends EntitySystem {

    //private final Sound enemyBulletHitsPlayerSound;
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);
    private ComponentMapper<HullComponent> hm = ComponentMapper.getFor(HullComponent.class);

    private ImmutableArray<Entity> enemyBulletEntities;
    private ImmutableArray<Entity> playerEntities;

    public EnemyBulletHitsPlayerSystem() {
        //enemyBulletHitsPlayerSound = assetManager.get("sounds/hit.ogg", Sound.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        enemyBulletEntities = engine.getEntitiesFor(Family.all(EnemyProjectileComponent.class, BoundingRectangleComponent.class).get());
        playerEntities = engine.getEntitiesFor(Family.all(PlayerComponent.class, BoundingRectangleComponent.class, HullComponent.class).get());
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
                    HullComponent h = hm.get(p);
                    h.hullCurrent-= 20.0f;
                    destroyed.add(e);
                }
            }
        }

        for(Entity e : destroyed)
        {
            //enemyBulletHitsPlayerSound.play(StaticConstants.sfxVolume);
            getEngine().removeEntity(e);
        }

    }

}
