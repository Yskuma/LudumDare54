package com.livelyspark.ludumdare54.prefab;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ai.AiChargeFastComponent;
import com.livelyspark.ludumdare54.components.ai.AiHoldComponent;
import com.livelyspark.ludumdare54.components.ai.AiSideStepComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyExploderComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyValueComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.rendering.ShapeComponent;
import com.livelyspark.ludumdare54.components.rendering.TextComponent;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.GunCollectionComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.enums.Shapes;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.FontKeys;
import com.livelyspark.ludumdare54.shipconstruction.GunPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartSingleShotSmall;

import java.util.ArrayList;

public class EnemyFactory {

    public static Entity FromKey(String key, float x, float y, float direction, TextureAtlas atlas) {
        if (key.equalsIgnoreCase("enemyHold")) {
            return EnemyHold(x, y, direction, atlas);
        }

        if (key.equalsIgnoreCase("enemyFastCharger")) {
            return EnemyFastCharger(x, y, direction, atlas);
        }

        if (key.equalsIgnoreCase("enemySideStep")) {
            return EnemySideStep(x, y, direction, atlas);
        }

        if (key.equalsIgnoreCase("enemyMine")) {
            return EnemyMine(x, y, direction, atlas);
        }

        if (key.equalsIgnoreCase("asteroid-brown-8-8")) {
            return Asteroid(x, y, direction, atlas, AtlasKeys.Asteroid_Brown_8_8, 8, 8);
        }

        if (key.equalsIgnoreCase("asteroid-brown-8-16")) {
            return Asteroid(x, y, direction, atlas, AtlasKeys.Asteroid_Brown_8_16, 8, 16);
        }

        if (key.equalsIgnoreCase("asteroid-brown-16-8")) {
            return Asteroid(x, y, direction, atlas, AtlasKeys.Asteroid_Brown_16_8, 16, 8);
        }

        if (key.equalsIgnoreCase("asteroid-brown-16-16")) {
            return Asteroid(x, y, direction, atlas, AtlasKeys.Asteroid_Brown_16_16, 16, 16);
        }

        if (key.equalsIgnoreCase("asteroid-brown-20-20")) {
            return Asteroid(x, y, direction, atlas, AtlasKeys.Asteroid_Brown_20_20, 20, 20);
        }

        if (key.equalsIgnoreCase("asteroid-brown-32-32")) {
            return Asteroid(x, y, direction, atlas, AtlasKeys.Asteroid_Brown_32_32, 32, 32);
        }

        return null;
    }

    public static Entity EnemyHold(float x, float y, float direction, TextureAtlas atlas) {
        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.5f, atlas.findRegions(AtlasKeys.Ship_Enemy_Hold), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        ArrayList<GunPartFitted> guns = new ArrayList<GunPartFitted>();
        guns.add(new GunPartFitted(new GunPartSingleShotSmall(), 0 , -7));

        e.add(new AnimationComponent(anim))

                .add(new HealthComponent(100, 0, 0, 0))
                .add(new EngineComponent(32,32))
                .add(new GeneratorComponent(100, 10))
                .add(new GunCollectionComponent(guns, true))

                .add(new AiHoldComponent(new Vector2(0, -100), 7f))
                .add(new EnemyValueComponent(100))

                .add(new BoundingRectangleComponent())
                .add(new TransformComponent(x, y, tr.getRegionWidth(), tr.getRegionHeight(), direction))
                .add(new VelocityComponent())
                .add(new EnemyComponent());
        return e;
    }

    public static Entity EnemyFastCharger(float x, float y, float direction, TextureAtlas atlas) {
        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.5f, atlas.findRegions(AtlasKeys.Ship_Enemy_Fast_Charger), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        ArrayList<GunPartFitted> guns = new ArrayList<GunPartFitted>();
        guns.add(new GunPartFitted(new GunPartSingleShotSmall(), 0 , -7));

        e.add(new AnimationComponent(anim))

                .add(new HealthComponent(100, 0, 0, 0))
                .add(new EngineComponent(32,32))
                .add(new GeneratorComponent(100, 10))
                .add(new GunCollectionComponent(guns, true))

                .add(new AiChargeFastComponent())
                .add(new EnemyValueComponent(100))

                .add(new BoundingRectangleComponent())
                .add(new TransformComponent(x, y, tr.getRegionWidth(), tr.getRegionHeight(), direction))
                .add(new VelocityComponent())
                .add(new EnemyComponent());

               return e;
    }

    public static Entity EnemySideStep(float x, float y, float direction, TextureAtlas atlas) {
        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.5f, atlas.findRegions(AtlasKeys.Ship_Enemy_Sidestep), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        ArrayList<GunPartFitted> guns = new ArrayList<GunPartFitted>();
        guns.add(new GunPartFitted(new GunPartSingleShotSmall(), -4 , -7));
        guns.add(new GunPartFitted(new GunPartSingleShotSmall(), 4 , -7));

        e.add(new AnimationComponent(anim))

                .add(new HealthComponent(100, 0, 0, 0))
                .add(new EngineComponent(32,32))
                .add(new GeneratorComponent(100, 10))
                .add(new GunCollectionComponent(guns, true))

                .add(new AiSideStepComponent(32))
                .add(new EnemyValueComponent(100))

                .add(new BoundingRectangleComponent())
                .add(new TransformComponent(x, y, tr.getRegionWidth(), tr.getRegionHeight(), direction))
                .add(new VelocityComponent())
                .add(new EnemyComponent());

        return e;
    }

    public static Entity EnemyMine(float x, float y, float direction, TextureAtlas atlas) {
        float radius = 48;
        float damage = 25;

        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.5f, atlas.findRegions(AtlasKeys.Ship_Enemy_Mine), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        e.add(new AnimationComponent(anim))
                .add(new BoundingRectangleComponent())
                .add(new TransformComponent(x, y, tr.getRegionWidth(), tr.getRegionHeight(), direction))
                .add(new VelocityComponent())
                .add(new EnemyComponent())
                .add(new EnemyExploderComponent(radius, damage))
                .add(new HealthComponent(200, 0, 0, 0))
                .add(new ShapeComponent(Shapes.ELLIPSE, new Color(255, 0, 0, 100), radius * 2, radius * 2, Vector2.Zero))
                .add(new TextComponent("DANGER!", FontKeys.Freedom8, new Color(255, 0, 0, 100), 16, new Vector2(0, 12)))
                .add(new EnemyValueComponent(200));
        return e;
    }

    public static Entity Asteroid(float x, float y, float direction, TextureAtlas atlas, String atlasKey, int width, int height) {

        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.5f, atlas.findRegions(atlasKey), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        e.add(new AnimationComponent(anim))
                .add(new BoundingRectangleComponent())
                .add(new TransformComponent(x, y, tr.getRegionWidth(), tr.getRegionHeight(), direction))
                .add(new VelocityComponent())
                .add(new EnemyComponent())
                .add(new HealthComponent(width*height*2, 0, 0, 0))
                .add(new EnemyValueComponent((width * height)/10));
        return e;
    }
}
