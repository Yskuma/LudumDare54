package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyProjectileComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerProjectileComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;

public abstract class ProjectileBase {

    public String textureKeyPlayer;
    public String textureKeyEnemy;
    public float damage;
    public float speed;


    public ProjectileBase()
    {
    }

    public Entity ToEntity(Vector2 position, Vector2 velocityBase, float direction, boolean playerShot, TextureAtlas atlas)
    {
        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(playerShot ? textureKeyPlayer : textureKeyEnemy), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);
        e.add(new AnimationComponent(anim));
        e.add(new TransformComponent(position.x, position.y, tr.getRegionWidth(),tr.getRegionHeight(), 0.0f));
        e.add(new BoundingRectangleComponent());

        Vector2 v = new Vector2(0, speed).rotateDeg(direction);
        v.add(velocityBase);
        VelocityComponent vc = new VelocityComponent(v.x,v.y);
        e.add(vc);

        if(playerShot)
        {
            e.add(new PlayerProjectileComponent(damage));
        }
        else
        {
            e.add(new EnemyProjectileComponent(damage));
        }

        return e;
    }
}
