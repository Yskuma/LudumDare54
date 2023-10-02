package com.livelyspark.ludumdare54.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class AiSideStepComponent implements Component {

    public float offsetMax = 32;
    public boolean moveRight = true;

    public Vector2 positionInitial;

    public AiSideStepComponent(float offsetMax)
    {
        this.offsetMax = offsetMax;
    }
}
