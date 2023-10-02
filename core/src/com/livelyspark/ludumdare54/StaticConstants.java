package com.livelyspark.ludumdare54;

import com.livelyspark.ludumdare54.enums.CostTiers;

import java.util.HashMap;

public class StaticConstants {
    public static final float camSpeed = 16;
    public static final float musicVolume = 0.1f;
    public static final float sfxVolume = 0.1f;
    public static final HashMap<CostTiers, Integer> costLookup = new HashMap<CostTiers, Integer>(){
        {
            put(CostTiers.Cheap, 40);
            put(CostTiers.Bargain, 100);
            put(CostTiers.Pricey, 250);
            put(CostTiers.Extortionate, 400);
        }
    };
}
