package com.livelyspark.ludumdare54.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.livelyspark.ludumdare54.GlobalGameState;
import com.livelyspark.ludumdare54.enums.Screens;
import com.livelyspark.ludumdare54.managers.IScreenManager;

public class YouWinSystem extends EntitySystem {

    private final IScreenManager screenManager;
    private final Stage stage;
    private final Skin uiSkin;
    private final float levelEnd;
    private final Camera camera;

    private float youWinTime = 0.0f;
    private float youWinThreshold = 2.0f;

    public YouWinSystem(IScreenManager screenManager, Camera camera, float levelEnd) {
        this.screenManager = screenManager;
        this.camera = camera;
        this.levelEnd = levelEnd;

        uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        stage = new Stage();
        Label titleLabel = new Label("Mission Complete", uiSkin, "title", Color.WHITE);
        stage.addActor(titleLabel);
    }

    @Override
    public void addedToEngine(Engine engine) {
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {

        if (camera.position.y > levelEnd) {
            youWinTime += deltaTime;
            stage.act();
            stage.draw();
        }

        if (youWinTime > youWinThreshold) {
            GlobalGameState.stageNum = GlobalGameState.stageNum + 1;
            GlobalGameState.moneyBanked = GlobalGameState.moneyBanked + GlobalGameState.moneyEarned;
            screenManager.switchScreen(Screens.Briefing);
        }
    }
}
