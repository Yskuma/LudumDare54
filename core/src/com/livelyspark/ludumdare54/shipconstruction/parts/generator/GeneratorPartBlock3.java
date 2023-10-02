package com.livelyspark.ludumdare54.shipconstruction.parts.generator;

import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.CostTiers;
import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class GeneratorPartBlock3 extends GeneratorPartBase {
    public GeneratorPartBlock3()
    {
        name = "Generator 3";
        iconAtlasKey = AtlasKeys.Part_Generator_T;

        cost = StaticConstants.costLookup.get(CostTiers.Pricey);

        energyMax = 80;
        energyRegen = 20;

        usedSlots[1][1] = true;
        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[2][0] = true;
    }
}
