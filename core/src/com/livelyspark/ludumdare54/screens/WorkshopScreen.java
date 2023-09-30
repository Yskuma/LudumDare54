package com.livelyspark.ludumdare54.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.systems.camera.CameraMovementSystem;
import com.livelyspark.ludumdare54.systems.cleanup.CleanHealthSystem;
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
import com.livelyspark.ludumdare54.systems.shield.ShieldRegenSystem;
import com.livelyspark.ludumdare54.systems.ui.DebugCameraDetailUiSystem;
import com.livelyspark.ludumdare54.systems.ui.DebugPlayerDetailUiSystem;

public class WorkshopScreen extends AbstractScreen {

    private Engine engine;
    private OrthographicCamera camera;

    public WorkshopScreen(IScreenManager screenManager, AssetManager assetManager) {
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

        // Animation Frames & Bounding Boxes
        engine.addSystem(new AnimationKeyframeUpdateSystem());

        //Render
        engine.addSystem(new SpriteRenderSystem(camera));

        //Cleanup

        //Debug
   }


    @Override
    public void hide() {
    }
}
