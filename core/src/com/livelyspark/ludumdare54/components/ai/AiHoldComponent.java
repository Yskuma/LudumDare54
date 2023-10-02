package com.livelyspark.ludumdare54.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class AiHoldComponent implements Component {

    public Vector2 positionTarget;
    public Vector2 positionOffset;
    public float holdTime;
    public float holdTimeCurrent = 0.0f;

    public AiHoldComponent(Vector2 positionOffset, float holdTime)
    {
        this.positionOffset = positionOffset;
        this.holdTime = holdTime;
    }
}
