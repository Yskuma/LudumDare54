package com.livelyspark.ludumdare54.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.livelyspark.ludumdare54.keys.TiledMapKeys;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.systems.camera.CameraMovementSystem;
import com.livelyspark.ludumdare54.systems.cleanup.CleanHealthSystem;
import com.livelyspark.ludumdare54.systems.cleanup.CleanLifespanSystem;
import com.livelyspark.ludumdare54.systems.cleanup.CleanOutOfBoundsSystem;
import com.livelyspark.ludumdare54.systems.collisions.EnemyBulletHitsPlayerSystem;
import com.livelyspark.ludumdare54.systems.collisions.PlayerBulletHitsEnemySystem;
import com.livelyspark.ludumdare54.systems.collisions.PlayerHitsEnemySystem;
import com.livelyspark.ludumdare54.systems.enemy.EnemySpawnSystem;
import com.livelyspark.ludumdare54.systems.energy.GeneratorRegenSystem;
import com.livelyspark.ludumdare54.gamestages.GameStage01System;
import com.livelyspark.ludumdare54.systems.gun.GunCooldownSystem;
import com.livelyspark.ludumdare54.systems.gun.ShootingSystem;
import com.livelyspark.ludumdare54.systems.physics.BoundingRectangleUpdateSystem;
import com.livelyspark.ludumdare54.systems.physics.MovementSystem;
import com.livelyspark.ludumdare54.systems.player.PlayerMovementRestrictionSystem;
import com.livelyspark.ludumdare54.systems.player.PlayerMovementSystem;
import com.livelyspark.ludumdare54.systems.player.PlayerShootingSystem;
import com.livelyspark.ludumdare54.systems.render.*;
import com.livelyspark.ludumdare54.systems.shield.ShieldRegenSystem;
import com.livelyspark.ludumdare54.systems.sound.SoundSystem;
import com.livelyspark.ludumdare54.systems.ui.DebugCameraDetailUiSystem;
import com.livelyspark.ludumdare54.systems.ui.DebugPlayerDetailUiSystem;

public class GameScreen extends AbstractScreen {

    private Engine engine;
    private OrthographicCamera camera;

    public GameScreen(IScreenManager screenManager, AssetManager assetManager) {
        super(screenManager, assetManager);
        engine = new Engine();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    ////948x533
    @Override
    public void show() {
        camera = new OrthographicCamera(256, 256);
        camera.position.x = 256/2;
        camera.position.y = 256/2;
        camera.update();

        TextureAtlas atlas = assetManager.get("textures/sprite-atlas.atlas", TextureAtlas.class);
        TiledMap map = assetManager.get(TiledMapKeys.Level1);

        //Stage Control
        engine.addSystem(new GameStage01System(atlas));
        engine.addSystem(new CameraMovementSystem(camera));
        engine.addSystem(new EnemySpawnSystem(camera, map, atlas));

        //Player
        engine.addSystem(new PlayerMovementSystem());
        engine.addSystem(new PlayerShootingSystem());
        engine.addSystem(new PlayerMovementRestrictionSystem(camera));

        //Move
        engine.addSystem(new MovementSystem());

        //Collisions
        engine.addSystem(new PlayerHitsEnemySystem());
        engine.addSystem(new PlayerBulletHitsEnemySystem());
        engine.addSystem(new EnemyBulletHitsPlayerSystem());

        //Mechanics
        engine.addSystem(new GunCooldownSystem());
        engine.addSystem(new ShootingSystem(atlas));
        engine.addSystem(new ShieldRegenSystem());
        engine.addSystem(new GeneratorRegenSystem());

        // Animation Frames & Bounding Boxes
        engine.addSystem(new AnimationKeyframeUpdateSystem());
        engine.addSystem(new BoundingRectangleUpdateSystem());

        //Render
        //engine.addSystem(new BackgroundRenderSystem(camera, gameState, assetManager));

        engine.addSystem(new TiledRenderSystem(camera, map));
        engine.addSystem(new SpriteRenderSystem(camera));
        engine.addSystem(new ShapeRenderSystem(camera));
        engine.addSystem(new TextRenderSystem(camera, assetManager));
        engine.addSystem(new HealthRenderSystem(camera, atlas));
        engine.addSystem(new EnergyRenderSystem(camera, atlas));

        //Cleanup
        engine.addSystem(new CleanOutOfBoundsSystem(camera));
        engine.addSystem(new CleanLifespanSystem());
        engine.addSystem(new CleanHealthSystem());

        //Sound
        engine.addSystem(new SoundSystem(assetManager));

        //Debug
        engine.addSystem(new DebugPlayerDetailUiSystem());
        engine.addSystem(new DebugCameraDetailUiSystem(camera));
        //engine.addSystem(new DebugBoundBoxRenderSystem(camera));
   }


    @Override
    public void hide() {
    }
}
