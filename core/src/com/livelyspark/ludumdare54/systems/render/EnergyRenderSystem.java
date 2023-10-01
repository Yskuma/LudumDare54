package com.livelyspark.ludumdare54.systems.render;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;

public class EnergyRenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;

    private ComponentMapper<GeneratorComponent> gm = ComponentMapper.getFor(GeneratorComponent.class);
    private ComponentMapper<BoundingRectangleComponent> rm = ComponentMapper.getFor(BoundingRectangleComponent.class);

    public EnergyRenderSystem(OrthographicCamera camera) {
        this.camera = camera;
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(GeneratorComponent.class, BoundingRectangleComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        if (camera == null) {
            return;
        }


        GeneratorComponent generator;
        BoundingRectangleComponent rect;

        camera.update();

        camera.update();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin();

        for (int i = 0; i < entities.size(); ++i) {

            Entity e = entities.get(i);

            generator = gm.get(e);
            rect = rm.get(e);

            renderer.setColor(Color.YELLOW);
            renderer.rect(rect.rectangle.x, rect.rectangle.y - 3, rect.rectangle.width * (generator.energyCurrent/generator.energyMax), 0.5f);

        }

        renderer.end();
    }
}
