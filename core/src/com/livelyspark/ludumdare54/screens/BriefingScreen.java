package com.livelyspark.ludumdare54.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.GlobalGameState;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.enums.Screens;
import com.livelyspark.ludumdare54.gamestages.Briefing01;
import com.livelyspark.ludumdare54.gamestages.Briefing02;
import com.livelyspark.ludumdare54.gamestages.Briefing03;
import com.livelyspark.ludumdare54.gamestages.IBriefing;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.systems.render.AnimationKeyframeUpdateSystem;
import com.livelyspark.ludumdare54.systems.render.SpriteRenderSystem;


public class BriefingScreen extends AbstractScreen {

    private Engine engine;
    private OrthographicCamera camera;
    private Stage stage;

    IBriefing briefing;

    float timeLive;

    public BriefingScreen(IScreenManager screenManager, AssetManager assetManager) {
        super(screenManager, assetManager);
        engine = new Engine();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        engine.update(delta);
        timeLive = timeLive + delta;

        stage.act();
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && timeLive > 0.5f) { // If the screen is touched after the game is done loading, go to the main menu screen
            screenManager.switchScreen(Screens.Shipyard);
        }
    }

    @Override
    public void resize(int width, int height) {

    }
////948x533
    @Override
    public void show() {
        briefing = GetBriefing();
        camera = new OrthographicCamera(256, 256);
        timeLive = 0f;

        stage = briefing.GetStage();

        addEntities();

        engine.addSystem(new AnimationKeyframeUpdateSystem());
        engine.addSystem(new SpriteRenderSystem(camera));
    }

    private void addEntities() {

        Texture background = assetManager.get(briefing.GetBackground(), Texture.class);
        TextureRegion tr = new TextureRegion(background);
        Animation<TextureRegion> anim = new Animation<TextureRegion>(1.0f, tr);
        engine.addEntity(new Entity()
                        .add(new AnimationComponent(anim))
                        .add(new TransformComponent(camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight, 0))
        );
    }

    @Override
    public void hide() {
    }

    private IBriefing GetBriefing()
    {
        switch(GlobalGameState.stageNum)
        {
            case 1:
                return new Briefing01();
            case 2:
                return new Briefing02();
            case 3:
                return new Briefing03();
            default:
                return null;
        }
    }
}
