package com.livelyspark.ludumdare54.shipconstruction.parts.hull;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class HullPartBlock1 extends HullPartBase {

    public HullPartBlock1()
    {
        name = "Hull 1";
        iconAtlasKey = AtlasKeys.Part_Hull_Small;

        cost = 40;

        hullMax = 10;

        usedSlots[0][0] = true;
    }
}
