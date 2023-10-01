package com.livelyspark.ludumdare54.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.livelyspark.ludumdare54.keys.FontKeys;
import com.livelyspark.ludumdare54.enums.Screens;
import com.livelyspark.ludumdare54.keys.SoundKeys;
import com.livelyspark.ludumdare54.keys.TiledMapKeys;
import com.livelyspark.ludumdare54.managers.IScreenManager;

public class LoadingScreen extends AbstractScreen {


    private Stage stage;

    private float startX, endX;
    private float percent;

    private Label titleLabel;
    private ProgressBar progressBar;

    public LoadingScreen(IScreenManager screenManager, AssetManager assetManager) {
        super(screenManager, assetManager);
    }

    @Override
    public void show() {
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));

        stage = new Stage();
        titleLabel = new Label("Loading", uiSkin, "font", Color.WHITE);
        progressBar = new ProgressBar(0f, 1f, 0.01f, false, uiSkin);

        loadAssets();
    }

    public void loadAssets() {
        // Add everything to be loaded, for instance:

        assetManager.load("textures/sprite-atlas.atlas", TextureAtlas.class);

        assetManager.load(SoundKeys.Pew, Sound.class);
        assetManager.load(SoundKeys.PewBig, Sound.class);
        assetManager.load(SoundKeys.Hit, Sound.class);
        assetManager.load(SoundKeys.Explosion, Sound.class);

        assetManager.load(FontKeys.Freedom8, BitmapFont.class);
        assetManager.load(FontKeys.Freedom12, BitmapFont.class);


        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load(TiledMapKeys.Level1, TiledMap.class);


        /*

        assetManager.load("textures/background_scifi/background_5.png", Texture.class);

        assetManager.load("sounds/explosion.ogg", Sound.class);

        assetManager.load("sounds/music_fantasy.ogg", Music.class);
        */

        assetManager.load("title_screen.png", Texture.class);

    }

    @Override
    public void resize(int width, int height) {
        progressBar.setWidth(stage.getWidth() * 0.8f);
        progressBar.setX((stage.getWidth() - progressBar.getWidth()) / 2);
        progressBar.setY((stage.getHeight() - progressBar.getHeight()) / 2);

        titleLabel.setX((stage.getWidth() - titleLabel.getWidth()) / 2);
        titleLabel.setY(progressBar.getY() + 50);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // True if we've finished loading assets
        if (assetManager.update()) {
            screenManager.switchScreen(Screens.MainMenu);
        }

        percent = Interpolation.linear.apply(percent, assetManager.getProgress(), 0.1f);
        progressBar.setValue(percent);

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        // Dispose the loading assets if we have any
        // assetManager.unload("data/loading.pack");
    }
}
