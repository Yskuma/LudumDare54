package com.livelyspark.ludumdare54.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.systems.camera.CameraMovementSystem;
import com.livelyspark.ludumdare54.systems.cleanup.CleanLifespanSystem;
import com.livelyspark.ludumdare54.systems.cleanup.CleanOutOfBoundsSystem;
import com.livelyspark.ludumdare54.systems.collisions.EnemyBulletHitsPlayerSystem;
import com.livelyspark.ludumdare54.systems.collisions.PlayerBulletHitsEnemySystem;
import com.livelyspark.ludumdare54.systems.collisions.PlayerHitsEnemySystem;
import com.livelyspark.ludumdare54.systems.gamestages.GameStage01System;
import com.livelyspark.ludumdare54.systems.physics.BoundingRectangleUpdateSystem;
import com.livelyspark.ludumdare54.systems.physics.MovementSystem;
import com.livelyspark.ludumdare54.systems.player.PlayerMovementRestrictionSystem;
import com.livelyspark.ludumdare54.systems.player.PlayerMovementSystem;
import com.livelyspark.ludumdare54.systems.player.PlayerShootingSystem;
import com.livelyspark.ludumdare54.systems.render.AnimationKeyframeUpdateSystem;
import com.livelyspark.ludumdare54.systems.render.DebugBoundBoxRenderSystem;
import com.livelyspark.ludumdare54.systems.render.HealthRenderSystem;
import com.livelyspark.ludumdare54.systems.render.SpriteRenderSystem;
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
        camera = new OrthographicCamera(768, 768);
        camera.position.x = 768/2;
        camera.position.y = 768/2;
        camera.update();

        TextureAtlas atlas = assetManager.get("textures/sprite-atlas.atlas", TextureAtlas.class);

        //Stage Control
        engine.addSystem(new GameStage01System(atlas));

        engine.addSystem(new CameraMovementSystem(camera));

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

        // Animation Frames & Bounding Boxes
        engine.addSystem(new AnimationKeyframeUpdateSystem());
        engine.addSystem(new BoundingRectangleUpdateSystem());

        //Render
        //engine.addSystem(new BackgroundRenderSystem(camera, gameState, assetManager));
        engine.addSystem(new SpriteRenderSystem(camera));
        engine.addSystem(new HealthRenderSystem(camera, atlas));

        //Cleanup
        engine.addSystem(new CleanOutOfBoundsSystem(camera));
        engine.addSystem(new CleanLifespanSystem());

        //Debug
        engine.addSystem(new DebugPlayerDetailUiSystem());
        engine.addSystem(new DebugCameraDetailUiSystem(camera));
        engine.addSystem(new DebugBoundBoxRenderSystem(camera));
   }


    @Override
    public void hide() {
    }
}
