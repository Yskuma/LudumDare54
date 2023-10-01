package com.livelyspark.ludumdare54.systems.render;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.ShapeComponent;
import com.livelyspark.ludumdare54.components.rendering.TextComponent;

public class TextRenderSystem extends EntitySystem {
    private final AssetManager assetManager;
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ComponentMapper<TextComponent> sm = ComponentMapper.getFor(TextComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public TextRenderSystem(OrthographicCamera camera, AssetManager assetMananger) {
        this.assetManager = assetMananger;
        this.camera = camera;
        batch = new SpriteBatch();
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TextComponent.class, TransformComponent.class).get());
    }

    @Override
    public void removedFromEngine (Engine engine) {

    }

    @Override
    public void update (float deltaTime) {
        if(camera == null){
            return;
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        for (int i = 0; i < entities.size(); ++i) {
            Entity e = entities.get(i);

            TextComponent text = sm.get(e);
            TransformComponent t = tm.get(e);

            BitmapFont font = assetManager.get(text.fontKey, BitmapFont.class);
            font.setColor(text.color);
            font.draw(batch, text.text, t.position.x + text.positionOffset.x, t.position.y + text.positionOffset.y, text.size, Align.center, false);

        }

        batch.end();
    }
}
