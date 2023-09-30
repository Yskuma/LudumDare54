package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

public class EnginePartBlock2 extends EnginePartBase {
    public EnginePartBlock2()
    {
        name = "Engine 2";

        speedMax = 50;
        accelMax = 20;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
