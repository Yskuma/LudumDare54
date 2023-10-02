package com.livelyspark.ludumdare54.utility;

import com.livelyspark.ludumdare54.components.ships.HealthComponent;

public class HealthHelper {

    public static void ApplyDamage(HealthComponent hc, float damage)
    {
        float shieldDamage = Math.min(damage, hc.shieldCurrent);
        float hullDamage = damage - shieldDamage;

        hc.shieldCurrent = hc.shieldCurrent - shieldDamage;
        hc.hullCurrent = hc.hullCurrent - hullDamage;
    }
}
