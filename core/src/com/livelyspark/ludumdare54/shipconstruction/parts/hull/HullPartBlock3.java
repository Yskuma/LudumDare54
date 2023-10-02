package com.livelyspark.ludumdare54.shipconstruction.parts.hull;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class HullPartBlock3 extends HullPartBase {

    public HullPartBlock3()
    {
        name = "Hull 3";
        iconAtlasKey = AtlasKeys.Part_Hull_Straight;

        hullMax = 70;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[2][0] = true;
        usedSlots[3][0] = true;
    }
}