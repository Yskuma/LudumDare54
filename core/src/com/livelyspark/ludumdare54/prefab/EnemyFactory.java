package com.livelyspark.ludumdare54.prefab;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.components.ai.AiMoveAndHoldComponent;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.ships.BaddieShip;
import com.livelyspark.ludumdare54.systems.enemy.EnemyAiMoveAndHoldSystem;

public class EnemyFactory {

    public static Entity FromKey(String key, float x, float y, float direction, TextureAtlas atlas)
    {
        if(key.equalsIgnoreCase("enemyDumbSingleShot")) {
            return DumbSingleShot(x, y, direction, atlas);
        }

        return null;
    }

    public static Entity DumbSingleShot(float x, float y, float direction, TextureAtlas atlas)
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
}
