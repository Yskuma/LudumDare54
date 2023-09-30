package com.livelyspark.ludumdare54.utility;

import com.livelyspark.ludumdare54.components.ships.HealthComponent;

public class HealthHelper {

    public static void ApplyDamage(HealthComponent hc, float damage)
    {
        if(hc.shieldCurrent > 0)
        {
            hc.shieldCurrent = hc.shieldCurrent - damage;
        }

        if(hc.shieldCurrent <= 0)
        {
            float remaining = hc.shieldCurrent * -1;
            hc.shieldCurrent = 0;

            hc.hullCurrent = hc.hullCurrent - remaining;
        }
    }
}
