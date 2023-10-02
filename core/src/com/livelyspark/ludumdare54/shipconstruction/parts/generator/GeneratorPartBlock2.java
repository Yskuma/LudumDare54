package com.livelyspark.ludumdare54.shipconstruction.parts.generator;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class GeneratorPartBlock2 extends GeneratorPartBase {
    public GeneratorPartBlock2()
    {
        name = "Generator 2";
        iconAtlasKey = AtlasKeys.Part_Generator_Medium;

        cost = StaticConstants.costLookup.get(CostTiers.Bargain);

        energyMax = 50;
        energyRegen = 25;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
