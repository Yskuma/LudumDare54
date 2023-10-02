package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBulletSmall;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileSmall;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartVulcanSmall extends GunPartBase {

    public final int offsetMax = 3;
    public int offsetCurrent = 0;
    public int offsetDirection = 1;

    public GunPartVulcanSmall()
    {
        name = "Vulcan";
        iconAtlasKey = AtlasKeys.Part_Weapon_Vulcan;

        cost = StaticConstants.costLookup.get(CostTiers.Pricey);

        cooldownMax = 0.1f;
        energyUsage = 2.0f;
        soundKey = SoundKeys.Pew;

        usedSlots[0][0] = true;
        usedSlots[0][1] = true;
        usedSlots[0][2] = true;
        usedSlots[1][0] = true;
    }

    @Override
    public ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas) {

        offsetCurrent = offsetCurrent + offsetDirection;
        if(offsetCurrent * offsetDirection == offsetMax)
        {
            offsetDirection = offsetDirection * -1;
        }

        ProjectileBase p = new ProjectileBulletSmall();
        Entity e = p.ToEntity(new Vector2(position.x + offsetCurrent, position.y), baseVelocity, direction, playerShot, atlas);
        return new ArrayList<Entity>(Arrays.asList(e));
    }
}
