package com.livelyspark.ludumdare54.systems.physics;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;


public class BoundingRectangleUpdateSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);

    public BoundingRectangleUpdateSystem() {
        super(Family.all(TransformComponent.class, AnimationComponent.class, BoundingRectangleComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        BoundingRectangleComponent rectangle = rm.get(entity);


        float width = transform.size.x;
        float height = transform.size.y;

        float x = transform.position.x;
        float y = transform.position.y;

        rectangle.rectangle.x = x;
        rectangle.rectangle.y = y;
        rectangle.rectangle.width = width;
        rectangle.rectangle.height = height;
    }
}
