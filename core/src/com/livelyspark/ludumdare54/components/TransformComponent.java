package com.livelyspark.ludumdare54.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.enums.RenderLayers;

public class TransformComponent implements Component {

    public Vector2 position;
    public Vector2 size;
    public float rotation;
    public RenderLayers renderLayer = RenderLayers.Normal;

    public TransformComponent(float x, float y, float width, float height, float rotation) {
        this.position = new Vector2(x,y);
        this.size = new Vector2(width,height);
        this.rotation = rotation;
    }
}
