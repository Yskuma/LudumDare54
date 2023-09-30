package com.livelyspark.ludumdare54.systems.sound;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.sound.SoundComponent;

import java.util.ArrayList;

public class SoundSystem extends EntitySystem {

    private final AssetManager assetManager;
    private ComponentMapper<SoundComponent> sm = ComponentMapper.getFor(SoundComponent.class);
    private ImmutableArray<Entity> entities;

    public SoundSystem(AssetManager assetManager){
        this.assetManager = assetManager;
    }

    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(SoundComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        for (Entity e : entities) {
            SoundComponent sc = sm.get(e);
            Sound sound = assetManager.get(sc.soundKey, Sound.class);
            sound.play(StaticConstants.sfxVolume);
        }

        for (Entity e : entities) {
            getEngine().removeEntity(e);
        }

    }

}
