package com.livelyspark.ludumdare54.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.livelyspark.ludumdare54.UI.ShipyardUI;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.systems.render.AnimationKeyframeUpdateSystem;
import com.livelyspark.ludumdare54.systems.render.SpriteRenderSystem;

public class ShipyardScreen extends AbstractScreen {

    private Engine engine;
    private Stage stage;
    private ShipyardUI shipyardUi;
    private OrthographicCamera camera;
    public ShipyardScreen(IScreenManager screenManager, AssetManager assetManager) {
        super(screenManager, assetManager);
        engine = new Engine();
        shipyardUi = new ShipyardUI();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(948, 533);

        stage = new Stage();

        shipyardUi.Show(stage);

        addEntities();

        engine.addSystem(new AnimationKeyframeUpdateSystem());
        engine.addSystem(new SpriteRenderSystem(camera));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.x = width/2;
        camera.position.y = height/2;
        camera.update();
    }

    @Override
    public void hide() {

    }

    private void addEntities() {

        Texture background = assetManager.get("title_screen.png", Texture.class);
        TextureRegion tr = new TextureRegion(background);
        Animation<TextureRegion> anim = new Animation<TextureRegion>(1.0f, tr);
        engine.addEntity(new Entity()
                .add(new AnimationComponent(anim))
                .add(new TransformComponent(camera.viewportWidth/2, camera.viewportHeight/2, tr.getRegionWidth(), tr.getRegionHeight(), 0))
        );
    }
}
