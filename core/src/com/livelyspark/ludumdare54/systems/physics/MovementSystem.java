package com.livelyspark.ludumdare54.systems.physics;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> mm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem () {
        super(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        VelocityComponent velocity = mm.get(entity);

        transform.position.add(new Vector2(velocity).scl(deltaTime));
    }
}
