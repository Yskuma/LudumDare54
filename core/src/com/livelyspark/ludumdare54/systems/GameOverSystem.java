package com.livelyspark.ludumdare54.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.enums.Screens;
import com.livelyspark.ludumdare54.managers.IScreenManager;

public class GameOverSystem extends EntitySystem {

    private final IScreenManager screenManager;
    private final Stage stage;
    private final Skin uiSkin;
    private ImmutableArray<Entity> entities;

    private float gameOverTime = 0.0f;
    private float gameOverThreshold = 2.0f;

    public GameOverSystem(IScreenManager screenManager) {
        this.screenManager = screenManager;
        uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        stage = new Stage();
        Label titleLabel = new Label("Game Over", uiSkin, "title", Color.WHITE);
        stage.addActor(titleLabel);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        if (entities.size() == 0) {
            gameOverTime += deltaTime;
            stage.act();
            stage.draw();
        }

        if (gameOverTime > gameOverThreshold) {
            screenManager.switchScreen(Screens.Briefing);
        }
    }
}
