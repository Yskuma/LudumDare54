package com.livelyspark.ludumdare54.systems.enemy;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.rendering.ShapeComponent;
import com.livelyspark.ludumdare54.components.rendering.TextComponent;
import com.livelyspark.ludumdare54.enums.Shapes;
import com.livelyspark.ludumdare54.keys.FontKeys;
import com.livelyspark.ludumdare54.prefab.EnemyFactory;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EnemySpawnSystem extends EntitySystem {

    private final OrthographicCamera camera;
    private final TiledMap tiledMap;
    private final TextureAtlas atlas;

    private ArrayList<QueuedEnemy> enemyQueue = new ArrayList<QueuedEnemy>();

    public EnemySpawnSystem(OrthographicCamera camera, TiledMap tiledMap, TextureAtlas atlas) {
        super();
        this.tiledMap = tiledMap;
        this.camera = camera;
        this.atlas = atlas;

        MapLayer layer = tiledMap.getLayers().get("Objects");
        MapObjects objects = layer.getObjects();

        /*
        MapProperties fmp = tiledMap.getProperties();
        int height = fmp.get("height", Integer.class);
        int tileHeight = fmp.get("tileheight", Integer.class);

        float coordHeight = height * tileHeight;
        */


        for (MapObject obj : objects) {
            MapProperties mp = obj.getProperties();
            String objType = mp.get("type", String.class);
            if(objType != null && objType.equalsIgnoreCase("enemy"))
            {
                String objTemplate = mp.get("template", String.class);
                float objX = mp.get("x", Float.class);
                float objY = mp.get("y", Float.class);

                if(objTemplate != null)
                {
                    enemyQueue.add(new QueuedEnemy(objX, objY, objTemplate));
                }
            }
        }

        Collections.sort(enemyQueue, new Comparator<QueuedEnemy>() {
            @Override
            public int compare(QueuedEnemy lhs, QueuedEnemy rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.y < rhs.y ? -1 : (lhs.y > rhs.y) ? 1 : 0;
            }
        });
    }

    @Override
    public void update (float deltaTime) {
        if(!enemyQueue.isEmpty())
        {
            QueuedEnemy q = enemyQueue.get(0);
            if(q.y < camera.position.y + (camera.viewportHeight / 2) + 32)
            {
                Entity enemy = EnemyFactory.FromKey(q.template, q.x,q.y,180, atlas);

                if(enemy != null)
                {
                    getEngine().addEntity(enemy);
                }

                enemyQueue.remove(0);
            }
        }

    }

    public class QueuedEnemy
    {
        public float x;
        public float y;
        public String template;

        public QueuedEnemy(float x, float y, String template)
        {
            this.x = x;
            this.y = y;
            this.template = template;
        }
    }

}
