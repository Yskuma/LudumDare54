package com.livelyspark.ludumdare54.shipconstruction.parts.shield;

import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;

public class ShieldPartBlock1 extends ShieldPartBase {

    public ShieldPartBlock1()
    {
        shieldMax = 10;
        shieldRegen = 5;
        shieldDelay = 1;

        usedSlots[0][0] = true;
    }
}
