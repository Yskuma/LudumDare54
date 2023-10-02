package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.player.PersistentProjectileComponent;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileSmall;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile.ProjectileWave;

import java.util.ArrayList;
import java.util.Arrays;

public class GunPartParticleWaveCannon extends GunPartBase {

    public GunPartParticleWaveCannon()
    {
        name = "Particle Wave";
        iconAtlasKey = AtlasKeys.Part_Weapon_Plasma;

        cost = StaticConstants.costLookup.get(CostTiers.Extortionate);

        cooldownMax = 2f;
        energyUsage = 200f;
        soundKey = SoundKeys.PewBig;

        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
        usedSlots[2][1] = true;
    }

    @Override
    public ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas) {
        ProjectileBase p = new ProjectileWave();
        Entity e = p.ToEntity(position, baseVelocity, direction, playerShot, atlas);
        e.add(new PersistentProjectileComponent());
        return new ArrayList<Entity>(Arrays.asList(e));
    }

}
