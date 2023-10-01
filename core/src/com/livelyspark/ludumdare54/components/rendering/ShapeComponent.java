package com.livelyspark.ludumdare54.components.rendering;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.enums.Shapes;

public class ShapeComponent implements Component {

    public Shapes shape;
    public Color color;
    public float width;
    public float height;
    public Vector2 positionOffset;

    public ShapeComponent(Shapes shape, Color color, float width, float height, Vector2 positionOffset)
    {
        this.shape = shape;
        this.color = color;
        this.width = width;
        this.height = height;
        this.positionOffset = positionOffset;
    }

}
