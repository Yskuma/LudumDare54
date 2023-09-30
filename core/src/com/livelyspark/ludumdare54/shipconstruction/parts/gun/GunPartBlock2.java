package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileRedSmall;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartBlock2 extends GunPartBase {
    public GunPartBlock2()
    {
        cooldownMax = 2.0f;
        energyUsage = 1.0f;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }

    @Override
    public ArrayList<Entity> Fire(int x, int y, float direction, boolean playerShot, TextureAtlas atlas) {
        ArrayList<Entity> projectileEnts = new ArrayList<Entity>();
        ProjectileBase p = new ProjectileRedSmall();
        for(int i = -1; i <= 1; i++)
        {
            projectileEnts.add(p.ToEntity(x, y, direction + (MathUtils.PI2 * 0.01f * i), playerShot, atlas));
        }

        return projectileEnts;
    }
}
