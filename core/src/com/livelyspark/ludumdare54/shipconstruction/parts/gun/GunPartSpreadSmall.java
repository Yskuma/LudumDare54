package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileSmall;

import java.util.ArrayList;

public class GunPartSpreadSmall extends GunPartBase {
    public GunPartSpreadSmall()
    {
        name = "Spread";
        iconAtlasKey = AtlasKeys.Part_Weapon_Spread;

        cost = StaticConstants.costLookup.get(CostTiers.Bargain);

        cooldownMax = 1.0f;
        energyUsage = 50.0f;
        soundKey = SoundKeys.PewBig;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }

    @Override
    public ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas) {
        ArrayList<Entity> projectileEnts = new ArrayList<Entity>();
        ProjectileBase p = new ProjectileSmall();
        for(int i = -1; i <= 1; i++)
        {
            projectileEnts.add(p.ToEntity(position, baseVelocity, direction + (5 * i), playerShot, atlas));
        }

        return projectileEnts;
    }
}
