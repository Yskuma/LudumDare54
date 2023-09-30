package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
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

    public Entity ToEntity(int x, int y, float direction, boolean playerShot, TextureAtlas atlas)
    {
        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(textureKey), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);
        e.add(new AnimationComponent(anim));
        e.add(new TransformComponent(x,y,tr.getRegionWidth(),tr.getRegionHeight(), 0.0f));

        Vector2 v = new Vector2(speed, 0).rotateRad(direction);
        VelocityComponent vc = new VelocityComponent(v.x,v.y);
        e.add(vc);

        if(playerShot)
        {
            e.add(new PlayerComponent());
        }
        else
        {
            e.add(new EnemyComponent());
        }

        return e;
    }
}
