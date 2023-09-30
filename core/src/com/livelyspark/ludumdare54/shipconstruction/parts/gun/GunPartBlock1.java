package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.enums.SoundKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileRedSmall;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartBlock1 extends GunPartBase {

    public GunPartBlock1()
    {
        cooldownMax = 0.5f;
        energyUsage = 100.0f;
        soundKey = SoundKeys.Pew;

        usedSlots[0][0] = true;
    }

    @Override
    public ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas) {
        ProjectileBase p = new ProjectileRedSmall();
        Entity e = p.ToEntity(position, baseVelocity, direction, playerShot, atlas);
        return new ArrayList<Entity>(Arrays.asList(e));
    }

}
