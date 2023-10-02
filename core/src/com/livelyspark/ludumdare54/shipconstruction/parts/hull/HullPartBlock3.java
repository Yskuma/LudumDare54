package com.livelyspark.ludumdare54.shipconstruction.parts.hull;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class HullPartBlock3 extends HullPartBase {

    public HullPartBlock3()
    {
        name = "A Brick Wall";
        iconAtlasKey = AtlasKeys.Part_Hull_Straight;

        cost = StaticConstants.costLookup.get(CostTiers.Pricey);

        hullMax = 70;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[2][0] = true;
        usedSlots[3][0] = true;
    }
}
