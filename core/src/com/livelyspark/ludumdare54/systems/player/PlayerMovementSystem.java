package com.livelyspark.ludumdare54.systems.player;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;

public class PlayerMovementSystem extends IteratingSystem {

    private int camspeed = 50;
    private int speed = 200;
    private int min = 0;
    private int max = 500;

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    private boolean lastPressed = false;

    public PlayerMovementSystem() {
        super(Family.all(PlayerComponent.class, TransformComponent.class, VelocityComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {

        boolean up = Gdx.input.isKeyPressed(Input.Keys.W) ||
                Gdx.input.isKeyPressed(Input.Keys.UP);

        boolean down = Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN);

        boolean right = Gdx.input.isKeyPressed(Input.Keys.D) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean left = Gdx.input.isKeyPressed(Input.Keys.A) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT);


        VelocityComponent vel = vm.get(entity);

        float y = camspeed;
        float x = 0;

        if(up)
        {
            y += speed;
        }

        if(down)
        {
            y += -speed;
        }

        if(right)
        {
            x += speed;
        }

        if(left)
        {
            x += -speed;
        }

        vel.set(new Vector2(x,y));

    }

}
