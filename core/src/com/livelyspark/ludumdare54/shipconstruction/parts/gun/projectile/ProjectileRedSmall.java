package com.livelyspark.ludumdare54.shipconstruction.parts.gun.projectile;

import com.livelyspark.ludumdare54.enums.AtlasRegions;

public class ProjectileRedSmall extends ProjectileBase{

    public ProjectileRedSmall()
    {
         damage = 20;
         speed = 200;
         textureKey = AtlasRegions.ProjectileRedSmall;
    }
}
