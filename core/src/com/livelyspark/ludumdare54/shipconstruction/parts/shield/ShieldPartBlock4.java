package com.livelyspark.ludumdare54.shipconstruction.parts.shield;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class ShieldPartBlock4 extends ShieldPartBase {

    public ShieldPartBlock4()
    {
        name = "S-1LLY";
        iconAtlasKey = AtlasKeys.Part_Shield_Z;

        cost = StaticConstants.costLookup.get(CostTiers.Extortionate);

        shieldMax = 200;
        shieldRegen = 20;

        usedSlots[1][0] = true;
        usedSlots[1][1] = true;
        usedSlots[0][1] = true;
        usedSlots[0][2] = true;
    }
}
