package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class EnginePartBlock2 extends EnginePartBase {
    public EnginePartBlock2()
    {
        name = "Engine 2";
        iconAtlasKey = AtlasKeys.Part_Engine_Medium;

        cost = 200;

        speedMax = 16 * 5;
        accelMax = 20;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
