package com.livelyspark.ludumdare54.shipconstruction.parts.shield;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;

public class ShieldPartBlock1 extends ShieldPartBase {

    public ShieldPartBlock1()
    {
        name = "Shield 1";
        iconAtlasKey = AtlasKeys.Part_Shield_Small;

        cost = StaticConstants.costLookup.get(CostTiers.Cheap);

        shieldMax = 10;
        shieldRegen = 5;
        shieldDelay = 1;

        usedSlots[0][0] = true;
    }
}
