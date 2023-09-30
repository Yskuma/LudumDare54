package com.livelyspark.ludumdare54.components.ships;

import com.badlogic.ashley.core.Component;

public class ShieldComponent implements Component {

    public float shieldCurrent;
    public float shieldMax;
    public float shieldRegen;
    public float shieldDelay;

    public float shieldLastDamaged;

    public ShieldComponent(float shieldMax, float shieldRegen, float shieldDelay)
    {
        this.shieldCurrent = shieldMax;
        this.shieldMax = shieldMax;

        this.shieldRegen = shieldRegen;
        this.shieldDelay = shieldDelay;
    }
}
