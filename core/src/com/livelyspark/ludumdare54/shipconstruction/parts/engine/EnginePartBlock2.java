package com.livelyspark.ludumdare54.shipconstruction.parts.engine;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class EnginePartBlock2 extends EnginePartBase {
    public EnginePartBlock2()
    {
        name = "2-FAT-V";
        iconAtlasKey = AtlasKeys.Part_Engine_Medium;

        cost = StaticConstants.costLookup.get(CostTiers.Bargain);

        speedMax = 16 * 4;
        accelMax = 20;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
