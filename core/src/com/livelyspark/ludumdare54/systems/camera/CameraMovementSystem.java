package com.livelyspark.ludumdare54.systems.camera;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.livelyspark.ludumdare54.StaticConstants;


public class CameraMovementSystem extends EntitySystem {

    private final OrthographicCamera camera;

    public CameraMovementSystem(OrthographicCamera camera)
    {
        this.camera = camera;
    }

    @Override
    public void update (float deltaTime) {
        camera.translate(new Vector3(0, StaticConstants.camSpeed,0).scl(deltaTime));
    }

}
