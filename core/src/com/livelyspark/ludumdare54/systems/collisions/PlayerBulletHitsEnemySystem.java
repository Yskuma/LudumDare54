package com.livelyspark.ludumdare54.systems.collisions;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.player.PlayerProjectileComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.HullComponent;

import java.util.ArrayList;


public class PlayerBulletHitsEnemySystem extends EntitySystem {

    //private final Sound playerBulletHitsEnemySound;
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);
    private ComponentMapper<HullComponent> hm = ComponentMapper.getFor(HullComponent.class);

    private ImmutableArray<Entity> enemyEntities;
    private ImmutableArray<Entity> playerBulletEntities;

    public PlayerBulletHitsEnemySystem() {
        //playerBulletHitsEnemySound = assetManager.get("sounds/hit.ogg", Sound.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        enemyEntities = engine.getEntitiesFor(Family.all(EnemyComponent.class, BoundingRectangleComponent.class, HullComponent.class).get());
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
                    HullComponent h = hm.get(e);
                    h.hullCurrent -= 50.0f;

                    destroyed.add(p);
                }
            }
        }

        for(Entity e : destroyed)
        {
            //playerBulletHitsEnemySound.play(StaticConstants.sfxVolume);
            getEngine().removeEntity(e);
        }

    }

}
