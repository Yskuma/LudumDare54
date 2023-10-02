package com.livelyspark.ludumdare54.shipconstruction.parts.hull;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class HullPartBlock2 extends HullPartBase {

    public HullPartBlock2()
    {
        name = "Hull 2";
        iconAtlasKey = AtlasKeys.Part_Hull_Medium;

        cost = StaticConstants.costLookup.get(CostTiers.Bargain);

        hullMax = 50;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
