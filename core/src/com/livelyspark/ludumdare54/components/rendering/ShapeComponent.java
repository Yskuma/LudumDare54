package com.livelyspark.ludumdare54.components.rendering;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.enums.Shapes;

public class ShapeComponent implements Component {

    public Shapes shape;
    public Color color;
    public float size;
    public Vector2 positionOffset;

    public ShapeComponent(Shapes shape, Color color, float size, Vector2 positionOffset)
    {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.positionOffset = positionOffset;
    }

}
