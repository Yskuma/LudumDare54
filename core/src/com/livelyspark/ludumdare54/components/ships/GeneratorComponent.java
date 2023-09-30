package com.livelyspark.ludumdare54.components.ships;

import com.badlogic.ashley.core.Component;

public class GeneratorComponent implements Component {

    public float energyCurrent;
    public float energyMax;
    public float energyRegen;

    public GeneratorComponent(float energyMax, float energyRegen)
    {
        this.energyCurrent = energyMax;
        this.energyMax = energyMax;
        this.energyRegen = energyRegen;
    }
}
