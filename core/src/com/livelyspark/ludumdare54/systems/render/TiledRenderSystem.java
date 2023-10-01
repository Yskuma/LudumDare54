package com.livelyspark.ludumdare54.systems.render;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledRenderSystem extends EntitySystem {

    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;
    private final TiledMap tiledMap;

    public TiledRenderSystem(OrthographicCamera camera, TiledMap tiledMap) {
        super();
        this.tiledMap = tiledMap;
        this.camera = camera;
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void update (float deltaTime) {
        renderer.setView(camera);
        renderer.render();
    }

}
