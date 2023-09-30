package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyProjectileComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.player.PlayerProjectileComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

public abstract class ProjectileBase {

    public String textureKey;
    public float damage;
    public float speed;

    //enum?
    //public int pathType;

    public ProjectileBase()
    {
    }

    public Entity ToEntity(Vector2 position, Vector2 velocityBase, float direction, boolean playerShot, TextureAtlas atlas)
    {
        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(textureKey), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);
        e.add(new AnimationComponent(anim));
        e.add(new TransformComponent(position.x - tr.getRegionWidth(), position.y - tr.getRegionHeight(), tr.getRegionWidth() * 2,tr.getRegionHeight() * 2, 0.0f));
        e.add(new BoundingRectangleComponent());

        Vector2 v = new Vector2(0, speed).rotateDeg(direction);
        v.add(velocityBase);
        VelocityComponent vc = new VelocityComponent(v.x,v.y);
        e.add(vc);

        if(playerShot)
        {
            e.add(new PlayerProjectileComponent());
        }
        else
        {
            e.add(new EnemyProjectileComponent());
        }

        return e;
    }
}
