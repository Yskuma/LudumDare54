package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ProjectileMedium extends ProjectileBase{

    public ProjectileMedium()
    {
         damage = 40;
         speed = 200;
         textureKeyPlayer = AtlasKeys.ProjectileBlueMedium;
         textureKeyEnemy = AtlasKeys.ProjectileRedMedium;
    }
}
