package com.livelyspark.ludumdare54.shipconstruction.parts.shield;

import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;

public class ShieldPartBlock2 extends ShieldPartBase {
    public ShieldPartBlock2()
    {
        name = "Shield 2";
        iconAtlasKey = AtlasKeys.Part_Shield_Medium;

        cost = 200;

        shieldMax = 50;
        shieldRegen = 25;

        usedSlots[0][0] = true;
        usedSlots[1][0] = true;
        usedSlots[0][1] = true;
        usedSlots[1][1] = true;
    }
}
