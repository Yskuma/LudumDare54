package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class EnginePartBlock3 extends EnginePartBase {
    public EnginePartBlock3()
    {
        name = "Engine 3";
        iconAtlasKey = AtlasKeys.Part_Engine_T;

        speedMax = 16 * 10;
        accelMax = 15;

        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
        usedSlots[2][1] = true;
    }
}
