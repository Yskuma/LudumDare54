package com.livelyspark.ludumdare54.shipconstruction.parts.generator;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class GeneratorPartBlock4 extends GeneratorPartBase {
    public GeneratorPartBlock4()
    {
        name = "Long Gone";
        iconAtlasKey = AtlasKeys.Part_Generator_Straight;

        cost = StaticConstants.costLookup.get(CostTiers.Bargain);

        energyMax = 50;
        energyRegen = 25;

        usedSlots[0][0] = true;
        usedSlots[0][1] = true;
        usedSlots[0][2] = true;
        usedSlots[0][3] = true;
    }
}
