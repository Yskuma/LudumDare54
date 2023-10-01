package com.livelyspark.ludumdare54.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class AiMoveAndHoldComponent implements Component {

    public Vector2 positionTarget;

    public Vector2 positionOffset;

    public AiMoveAndHoldComponent(Vector2 positionOffset)
    {
        this.positionOffset = positionOffset;
    }
}
