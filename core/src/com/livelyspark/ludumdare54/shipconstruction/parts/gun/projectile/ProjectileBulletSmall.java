package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ProjectileBulletSmall extends ProjectileBase{

    public ProjectileBulletSmall()
    {
         damage = 10;
         speed = 300;
         textureKeyPlayer = AtlasKeys.ProjectileBlueBulletSmall;
         textureKeyEnemy = AtlasKeys.ProjectileRedBulletSmall;
    }
}
