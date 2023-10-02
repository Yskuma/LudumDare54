package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ProjectileWave extends ProjectileBase{

    public ProjectileWave()
    {
         damage = 100;
         speed = 100;
         textureKeyPlayer = AtlasKeys.ProjectileBlueWave;
         textureKeyEnemy = AtlasKeys.ProjectileBlueWave;
    }
}
