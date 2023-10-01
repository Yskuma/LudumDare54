package com.livelyspark.ludumdare54.prefab;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ai.AiMoveAndHoldComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyExploderComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.rendering.ShapeComponent;
import com.livelyspark.ludumdare54.components.rendering.TextComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.enums.Shapes;
import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.keys.FontKeys;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.ships.BaddieShip;

public class EnemyFactory {

    public static Entity FromKey(String key, float x, float y, float direction, TextureAtlas atlas)
    {
        if(key.equalsIgnoreCase("enemyDumbSingleShot")) {
            return EnemyDumbSingleShot(x, y, direction, atlas);
        }

        if(key.equalsIgnoreCase("enemyMine")) {
            return EnemyMine(x, y, direction, atlas);
        }

        return null;
    }

    public static Entity EnemyDumbSingleShot(float x, float y, float direction, TextureAtlas atlas)
    {
        ShipBase ship = new BaddieShip();
        ship.shipParts.add(new ShipPartFitted(new EnginePartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GeneratorPartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new HullPartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new ShieldPartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GunPartBlock1(),0,0));
        return ship.ToEntity(x,y,direction, false, atlas)
                .add(new AiMoveAndHoldComponent(new Vector2(0, -100)));
    }

    public static Entity EnemyMine(float x, float y, float direction, TextureAtlas atlas)
    {
        float radius = 48;
        float damage = 25;

        Entity e = new Entity();
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.5f, atlas.findRegions(AtlasKeys.Mine), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        e.add(new AnimationComponent(anim));
        e.add(new BoundingRectangleComponent());
        e.add(new TransformComponent(x, y, tr.getRegionWidth(),tr.getRegionHeight(), direction));
        e.add(new VelocityComponent());
        e.add(new EnemyComponent());
        e.add(new EnemyExploderComponent(radius, damage));
        e.add(new HealthComponent(200, 0 ,0, 0));
        e.add(new ShapeComponent(Shapes.ELLIPSE, new Color(255,0,0,100),radius * 2,radius * 2, Vector2.Zero));
        e.add(new TextComponent("DANGER!", FontKeys.Freedom8, new Color(255,0,0,100), 16, new Vector2(0, -8)));
        return e;
    }
}
