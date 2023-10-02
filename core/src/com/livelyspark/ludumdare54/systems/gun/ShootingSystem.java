package com.livelyspark.ludumdare54.systems.gun;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.GunCollectionComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;

import java.util.ArrayList;


public class ShootingSystem extends IteratingSystem {

    private final TextureAtlas atlas;
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<GeneratorComponent> gm = ComponentMapper.getFor(GeneratorComponent.class);
    private ComponentMapper<GunCollectionComponent> gcm = ComponentMapper.getFor(GunCollectionComponent.class);

    private ComponentMapper<PlayerComponent> pm = ComponentMapper.getFor(PlayerComponent.class);

    public ShootingSystem(TextureAtlas atlas) {
        super(Family.all(TransformComponent.class, VelocityComponent.class, GeneratorComponent.class, GunCollectionComponent.class).get());
        this.atlas = atlas;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        TransformComponent t = tm.get(entity);
        VelocityComponent v = vm.get(entity);
        GeneratorComponent g = gm.get(entity);
        GunCollectionComponent gc = gcm.get(entity);
        PlayerComponent p = pm.get(entity);

        if(!gc.isTriggered)
        {
            return;
        }

        for (GunPartBase gunpart: gc.gunParts) {
            if(gunpart.cooldownCurrent >= gunpart.cooldownMax &&
            gunpart.energyUsage <= g.energyCurrent)
            {
                boolean isPlayer = p != null;

                Vector2 sourcePos =  new Vector2(t.position);

                ArrayList<Entity> projs = gunpart.Fire(sourcePos, Vector2.Zero, t.rotation, isPlayer, atlas);
                for(Entity proj : projs)
                {
                    getEngine().addEntity(proj);
                }
                g.energyCurrent = g.energyCurrent - gunpart.energyUsage;
                gunpart.cooldownCurrent = 0;

                if(gunpart.soundKey != null && gunpart.soundKey != "")
                {
                    getEngine().addEntity(new Entity().add(new SoundComponent(gunpart.soundKey)));
                }
            }
        }
        
        
    }

}
