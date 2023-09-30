package com.livelyspark.ludumdare54.systems.player;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.GunCollectionComponent;


public class PlayerShootingSystem extends IteratingSystem {
    private ComponentMapper<PlayerComponent> pm = ComponentMapper.getFor(PlayerComponent.class);
    private ComponentMapper<GunCollectionComponent> gcm = ComponentMapper.getFor(GunCollectionComponent.class);

    public PlayerShootingSystem() {
        super(Family.all(PlayerComponent.class, GunCollectionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean isPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                || Gdx.input.isKeyPressed(Input.Keys.SPACE);

        GunCollectionComponent gc = gcm.get(entity);
        gc.isTriggered = isPressed;
    }

}
