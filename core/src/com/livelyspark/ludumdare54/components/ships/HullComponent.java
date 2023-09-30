package com.livelyspark.ludumdare54.components.ships;

import com.badlogic.ashley.core.Component;

public class HullComponent implements Component {

    public float hullCurrent;
    public float hullMax;

    public HullComponent(float hullMax)
    {
        this.hullCurrent = hullMax;
        this.hullMax = hullMax;
    }
}
