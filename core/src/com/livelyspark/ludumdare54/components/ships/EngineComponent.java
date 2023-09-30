package com.livelyspark.ludumdare54.components.ships;

import com.badlogic.ashley.core.Component;

public class EngineComponent implements Component {

    public float speedMax;
    public float accelMax;

    public EngineComponent(float speedMax, float accelMax)
    {
        this.speedMax = speedMax;
        this.accelMax = accelMax;
    }
}
