package com.livelyspark.ludumdare54.systems.render;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.ShapeComponent;

public class ShapeRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private OrthographicCamera camera;
    private ShapeRenderer renderer;

    private ComponentMapper<ShapeComponent> sm = ComponentMapper.getFor(ShapeComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public ShapeRenderSystem(OrthographicCamera camera) {
        renderer = new ShapeRenderer();

        //TODO: Fix so shapes are sorted by Type and we manually flush between types
        renderer.setAutoShapeType(true);
        this.camera = camera;
    }

    @Override
    public void addedToEngine (Engine engine) {
        entities = engine.getEntitiesFor(Family.all(ShapeComponent.class, TransformComponent.class).get());
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
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin();


        for (int i = 0; i < entities.size(); ++i) {
            Entity e = entities.get(i);

            ShapeComponent shape = sm.get(e);
            TransformComponent t = tm.get(e);

            renderer.setColor(shape.color);

            switch(shape.shape)
            {
                case RECTANGLE:
                    renderer.rect(t.position.x - (shape.width/2) + shape.positionOffset.x,
                                  t.position.y - (shape.height/2) + shape.positionOffset.y,
                                     shape.width,shape.height);
                    break;
                case ELLIPSE:
                    renderer.ellipse(t.position.x - (shape.width/2) + shape.positionOffset.x,
                                    t.position.y - (shape.height/2) + shape.positionOffset.y,
                                       shape.width,shape.height);
                    break;
            }
        }

        renderer.end();
    }
}
