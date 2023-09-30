package com.livelyspark.ludumdare54.components.ships;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {

    public float hullCurrent;
    public float hullMax;

    public float shieldCurrent;
    public float shieldMax;
    public float shieldRegen;
    public float shieldDelay;

    public float shieldLastDamaged;

    public HealthComponent(float hullMax, float shieldMax, float shieldRegen, float shieldDelay)
    {
        this.hullCurrent = hullMax;
        this.hullMax = hullMax;

        this.shieldCurrent = shieldMax;
        this.shieldMax = shieldMax;

        this.shieldRegen = shieldRegen;
        this.shieldDelay = shieldDelay;
    }
}
