package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

public class EnginePartBlock1 extends EnginePartBase {
    public EnginePartBlock1()
    {
        name = "Engine 1";
        iconAtlasKey = AtlasKeys.Part_Engine_Small;

        speedMax = 16;
        accelMax = 5;

        usedSlots[0][0] = true;
    }
}
