package com.livelyspark.ludumdare54.prefab;

import com.livelyspark.ludumdare54.shipconstruction.ShipBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.ships.BaddieShip;

public class EnemyFactory {

    public static ShipBase DumbSingleShot()
    {
        ShipBase ship = new BaddieShip();
        ship.shipParts.add(new ShipPartFitted(new EnginePartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GeneratorPartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new HullPartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new ShieldPartBlock1(),0,0));
        ship.shipParts.add(new ShipPartFitted(new GunPartBlock1(),0,0));
        return ship;
    }
}
