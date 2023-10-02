package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ProjectileBulletLarge extends ProjectileBase{

    public ProjectileBulletLarge()
    {
         damage = 20;
         speed = 300;
         textureKeyPlayer = AtlasKeys.ProjectileBlueBulletLarge;
         textureKeyEnemy = AtlasKeys.ProjectileRedBulletLarge;
    }
}
