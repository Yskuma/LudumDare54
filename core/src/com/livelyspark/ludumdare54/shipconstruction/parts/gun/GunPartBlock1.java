package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileRedSmall;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartBlock1 extends GunPartBase {

    public GunPartBlock1()
    {
        cooldownMax = 1.0f;
        energyUsage = 1.0f;

        usedSlots[0][0] = true;
    }

    @Override
    public ArrayList<Entity> Fire(int x, int y, float direction, boolean playerShot, TextureAtlas atlas) {
        ProjectileBase p = new ProjectileRedSmall();
        Entity e = p.ToEntity(x, y, direction, playerShot, atlas);
        return new ArrayList<Entity>(Arrays.asList(e));
    }

}
