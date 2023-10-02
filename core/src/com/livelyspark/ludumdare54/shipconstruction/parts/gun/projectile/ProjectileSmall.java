package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ProjectileSmall extends ProjectileBase{

    public ProjectileSmall()
    {
         damage = 10;
         speed = 200;
         textureKeyPlayer = AtlasKeys.ProjectileBlueSmall;
         textureKeyEnemy = AtlasKeys.ProjectileRedSmall;
    }
}
