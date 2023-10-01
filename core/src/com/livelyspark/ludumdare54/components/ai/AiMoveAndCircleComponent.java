package com.livelyspark.ludumdare54.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class AiMoveAndCircleComponent implements Component {
    private float radius;
    public Vector2 positionInitial;
    public Vector2 positionOffset;

    public AiMoveAndCircleComponent(Vector2 positionOffset, float radius)
    {
        this.positionOffset = positionOffset;
        this.radius = radius;
    }
}
