package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.enums.SoundKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileGreenSmall;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileRedSmall;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartBlock2 extends GunPartBase {
    public GunPartBlock2()
    {
        name = "Spread";

        cooldownMax = 1.0f;
        energyUsage = 100.0f;
        soundKey = SoundKeys.PewBig;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }

    @Override
    public ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas) {
        ArrayList<Entity> projectileEnts = new ArrayList<Entity>();
        ProjectileBase p = new ProjectileGreenSmall();
        for(int i = -1; i <= 1; i++)
        {
            projectileEnts.add(p.ToEntity(position, baseVelocity, direction + (5 * i), playerShot, atlas));
        }

        return projectileEnts;
    }
}
