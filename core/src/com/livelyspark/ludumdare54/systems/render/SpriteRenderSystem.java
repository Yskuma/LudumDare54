package com.livelyspark.ludumdare54.systems.render;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.enums.RenderLayers;

public class SpriteRenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private float stateTime = 0.0f;

    private ComponentMapper<AnimationComponent> sm = ComponentMapper.getFor(AnimationComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public SpriteRenderSystem(OrthographicCamera camera) {
        batch = new SpriteBatch();
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(AnimationComponent.class, TransformComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {

    }

    @Override
    public void update(float deltaTime) {
        if (camera == null) {
            return;
        }

        stateTime += deltaTime;

        AnimationComponent animation;
        TransformComponent t;

        camera.update();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        for (RenderLayers renderLayer : new RenderLayers[]{RenderLayers.BackgroundImage, RenderLayers.Background, RenderLayers.Normal, RenderLayers.Foreground})
        {
            for (int i = 0; i < entities.size(); ++i) {

                Entity e = entities.get(i);
                t = tm.get(e);

                if(t.renderLayer != renderLayer)
                {
                    continue;
                }

                animation = sm.get(e);

                TextureRegion tex = animation.getCurrentKeyframe();
                //draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
                //		float scaleX, float scaleY, float rotation)
                batch.draw(tex,
                        t.position.x,
                        t.position.y,
                        t.size.x / 2,
                        t.size.y / 2,
                        t.size.x,
                        t.size.y,
                        1,
                        1,
                        t.rotation
                );
            }

        }

        batch.end();
    }
}
