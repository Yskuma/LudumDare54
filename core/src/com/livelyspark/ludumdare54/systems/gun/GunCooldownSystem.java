package com.livelyspark.ludumdare54.systems.gun;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.GunCollectionComponent;
import com.livelyspark.ludumdare54.shipconstruction.GunPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;

import java.util.ArrayList;


public class GunCooldownSystem extends IteratingSystem {

    private ComponentMapper<GunCollectionComponent> gcm = ComponentMapper.getFor(GunCollectionComponent.class);

    public GunCooldownSystem() {
        super(Family.all(GunCollectionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        GunCollectionComponent gc = gcm.get(entity);

        if(!gc.isTriggered)
        {
            return;
        }

        for (GunPartFitted gunpartFitted: gc.gunParts) {
            gunpartFitted.gunPart.cooldownCurrent =  gunpartFitted.gunPart.cooldownCurrent + deltaTime;
        }
        
        
    }

}
