package com.livelyspark.ludumdare54.shipconstruction;

import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;

public class GunPartFitted {
    public int x;
    public int y;
    public GunPartBase gunPart;

    public GunPartFitted(GunPartBase gunPart, int x, int y)
    {
        this.gunPart = gunPart;
        this.x = x;
        this.y = y;
    }
}
