package com.livelyspark.ludumdare54.shipconstruction.parts.generator;

public class GeneratorPartBlock2 extends GeneratorPartBase {
    public GeneratorPartBlock2()
    {
        name = "Generator 2";

        energyMax = 50;
        energyRegen = 25;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
