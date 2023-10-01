package com.livelyspark.ludumdare54.systems.render;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;

public class HealthRenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;

    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);

    public HealthRenderSystem(OrthographicCamera camera) {
        this.camera = camera;
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(HealthComponent.class, BoundingRectangleComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        if (camera == null) {
            return;
        }


        HealthComponent health;
        BoundingRectangleComponent rect;

        camera.update();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin();

        for (int i = 0; i < entities.size(); ++i) {

            Entity e = entities.get(i);

            health = hm.get(e);
            rect = rm.get(e);

            renderer.setColor(Color.RED);
            renderer.rect(rect.rectangle.x, rect.rectangle.y - 1, rect.rectangle.width * (health.hullCurrent/health.hullMax), 0.5f);

            renderer.setColor(Color.BLUE);
            renderer.rect(rect.rectangle.x, rect.rectangle.y - 2, rect.rectangle.width * (health.shieldCurrent/health.shieldMax), 0.5f);

        }

        renderer.end();
    }
}
