package com.livelyspark.ludumdare54.systems.player;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;


public class PlayerMovementRestrictionSystem extends IteratingSystem {

    private final OrthographicCamera camera;

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public PlayerMovementRestrictionSystem(OrthographicCamera camera) {
        super(Family.all(PlayerComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        TransformComponent t = tm.get(entity);

        t.position.y = Math.min(camera.position.y + (camera.viewportHeight / 2) - (t.size.y/2), t.position.y);
        t.position.y = Math.max(camera.position.y - (camera.viewportHeight / 2) + (t.size.y/2), t.position.y);

        t.position.x = Math.min(camera.position.x + (camera.viewportWidth / 2) - (t.size.x/2), t.position.x);
        t.position.x = Math.max(camera.position.x - (camera.viewportWidth / 2) + (t.size.x/2), t.position.x);
    }

}
