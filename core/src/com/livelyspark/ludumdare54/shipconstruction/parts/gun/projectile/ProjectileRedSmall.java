package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.enums.AtlasRegions;

public class ProjectileRedSmall extends ProjectileBase{

    public ProjectileRedSmall()
    {
         damage = 2;
         speed = 100;
         textureKey = AtlasRegions.ProjectileRedSmall;
    }
}
