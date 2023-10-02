package com.livelyspark.ludumdare54.shipconstruction.parts.generator;

import com.livelyspark.ludumdare54.keys.AtlasKeys;

public class GeneratorPartBlock1 extends GeneratorPartBase {

    public GeneratorPartBlock1()
    {
        name = "Generator 1";
        iconAtlasKey = AtlasKeys.Part_Generator_Small;

        energyMax = 10;
        energyRegen = 5;

        usedSlots[0][0] = true;
    }
}
