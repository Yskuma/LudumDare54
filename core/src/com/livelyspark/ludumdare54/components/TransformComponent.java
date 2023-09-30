package com.livelyspark.ludumdare54.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {

    public Vector2 position;
    public Vector2 size;
    public float rotation;
    public TransformComponent(float x, float y, float height, float width, float rotation) {
        this.position = new Vector2(x,y);
        this.size = new Vector2(height,width);
        this.rotation = rotation;
    }
}
