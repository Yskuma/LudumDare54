package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileSmall;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartSingleShotSmall extends GunPartBase {

    public GunPartSingleShotSmall()
    {
        name = "Simple Gun";
        iconAtlasKey = AtlasKeys.Part_Weapon_Single;

        cooldownMax = 0.5f;
        energyUsage = 25.0f;
        soundKey = SoundKeys.Pew;

        usedSlots[0][0] = true;
    }

    @Override
    public ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas) {
        ProjectileBase p = new ProjectileSmall();
        Entity e = p.ToEntity(position, baseVelocity, direction, playerShot, atlas);
        return new ArrayList<Entity>(Arrays.asList(e));
    }

}
