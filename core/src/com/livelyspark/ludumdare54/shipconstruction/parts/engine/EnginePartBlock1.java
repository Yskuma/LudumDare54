package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

public class EnginePartBlock1 extends EnginePartBase {
    public EnginePartBlock1()
    {
        name = "Engine 1";

        speedMax = 16;
        accelMax = 5;

        usedSlots[0][0] = true;
    }
}
