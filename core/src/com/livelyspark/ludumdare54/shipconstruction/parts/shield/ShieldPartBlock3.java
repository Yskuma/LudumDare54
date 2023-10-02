package com.livelyspark.ludumdare54.shipconstruction.parts.shield;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ShieldPartBlock3 extends ShieldPartBase {

    public ShieldPartBlock3()
    {
        name = "Shield 3";
        iconAtlasKey = AtlasKeys.Part_Shield_T;

        cost = StaticConstants.costLookup.get(CostTiers.Pricey);

        shieldMax = 200;
        shieldRegen = 20;
        shieldDelay = 1;

        usedSlots[0][0] = true;
        usedSlots[1][1] = true;
        usedSlots[0][1] = true;
        usedSlots[0][2] = true;
    }
}
