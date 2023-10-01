package com.livelyspark.ludumdare54.components.rendering;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.enums.Shapes;

public class TextComponent implements Component {

    public String text;
    public Color color;
    public float size;
    public Vector2 positionOffset;


    public TextComponent(String text, Color color, float size, Vector2 positionOffset)
    {
        this.text = text;
        this.color = color;
        this.size = size;
        this.positionOffset = positionOffset;
    }

}
