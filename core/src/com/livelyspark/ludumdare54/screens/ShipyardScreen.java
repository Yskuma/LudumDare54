package com.livelyspark.ludumdare54.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.livelyspark.ludumdare54.UI.ShipyardUISystem;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.enums.RenderLayers;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;
import com.livelyspark.ludumdare54.systems.render.AnimationKeyframeUpdateSystem;
import com.livelyspark.ludumdare54.systems.render.SpriteRenderSystem;
import com.livelyspark.ludumdare54.systems.render.TiledRenderSystem;

public class ShipyardScreen extends AbstractScreen {

    private Engine engine;
    private Stage stage;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer tiledRenderer;

    TextureAtlas atlas;

    public ShipyardScreen(IScreenManager screenManager, AssetManager assetManager) {
        super(screenManager, assetManager);
        this.atlas = assetManager.get("textures/sprite-atlas.atlas", TextureAtlas.class);
        engine = new Engine();

    }

    private BlockShip PlayerShipTemp()
    {
        BlockShip ship = new BlockShip();
        ship.shipParts.add(new ShipPartFitted(new EnginePartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GeneratorPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new HullPartBlock2(),0,0));
        ship.shipParts.add(new ShipPartFitted(new ShieldPartBlock2(),0,0));
        return ship;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(768, 768);
        camera.position.x = 768/2;
        camera.position.y = 768/2;

        camera.update();

        stage = new Stage();

        addEntities();

        engine.addSystem(new AnimationKeyframeUpdateSystem());
        engine.addSystem(new SpriteRenderSystem(camera));
        engine.addSystem(new ShipyardUISystem(stage, atlas, PlayerShipTemp(), screenManager));

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
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

        TransformComponent transformComponent = new TransformComponent(camera.viewportWidth/2, camera.viewportHeight/2, tr.getRegionWidth(), tr.getRegionHeight(), 0);
        transformComponent.renderLayer = RenderLayers.BackgroundImage;
        engine.addEntity(new Entity()
                .add(new AnimationComponent(anim))
                .add(transformComponent)
        );
    }
}
