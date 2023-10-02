package com.livelyspark.ludumdare54.utility;

import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartSingleShotSmall;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartSpreadSmall;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartVulcanSmall;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock2;

public class PartProvider {

    public static ShipPartBase GetPart(BuildButton part)
    {
        switch (part){
            case Engine1:
                return new EnginePartBlock1();
            case Engine2:
                return new EnginePartBlock2();
            case Generator1:
                return new GeneratorPartBlock1();
            case Generator2:
                return new GeneratorPartBlock2();
            case Gun1:
                return new GunPartSingleShotSmall();
            case Gun2:
                return new GunPartSpreadSmall();
            case Gun3:
                return new GunPartVulcanSmall();
            case Hull1:
                return new HullPartBlock1();
            case Hull2:
                return new HullPartBlock2();
            case Shield1:
                return new ShieldPartBlock1();
            case Shield2:
                return new ShieldPartBlock2();
            default:
                return null;
        }
    }

}
