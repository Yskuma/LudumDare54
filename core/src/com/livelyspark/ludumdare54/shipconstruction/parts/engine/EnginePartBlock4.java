package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class EnginePartBlock4 extends EnginePartBase {
    public EnginePartBlock4()
    {
        name = "Engine 4";
        iconAtlasKey = AtlasKeys.Part_Engine_Z;

        cost = StaticConstants.costLookup.get(CostTiers.Pricey);

        speedMax = 16 * 4;
        accelMax = 40;

        usedSlots[1][0] = true;
        usedSlots[2][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
