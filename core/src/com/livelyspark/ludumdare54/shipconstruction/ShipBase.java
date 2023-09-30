package com.livelyspark.ludumdare54.shipconstruction;

import com.badlogic.ashley.core.Entity;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.HullComponent;
import com.livelyspark.ludumdare54.components.ships.ShieldComponent;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBase;

import java.util.ArrayList;

public class ShipBase {

    public boolean[][] partSlots = new boolean[16][16];

    public ArrayList<ShipPartFitted> shipParts = new ArrayList<ShipPartFitted>();

    public ShipBase()
    {
    }


    public int[][] GetFilledSlots() {

        //This is a fudge but if anything is outside [16][16] it's bad
        int[][] filledSlots = new int[32][32];

        for (ShipPartFitted fittedPart : shipParts) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j <= 16; j++) {

                    if(fittedPart.shipPart.usedSlots[i][j])
                    {
                        filledSlots[i+ fittedPart.x][j+ fittedPart.y]++;
                    }

                }
            }
        }

        return filledSlots;
    }

    public Entity ToEntity()
    {
        Entity e = new Entity();

        // Most ship systems have a base value
        HullComponent hull = new HullComponent(100);
        ShieldComponent shield = new ShieldComponent(0, 0, 10);
        GeneratorComponent generator = new GeneratorComponent(100, 10);
        EngineComponent engine = new EngineComponent(0, 0);

        for (ShipPartFitted fittedPart: shipParts) {

            if(fittedPart.shipPart instanceof HullPartBase)
            {
                HullPartBase p = (HullPartBase) fittedPart.shipPart;
                hull.hullMax += p.hullMax;
                hull.hullCurrent += p.hullMax;
            }

            if(fittedPart.shipPart instanceof ShieldPartBase)
            {
                ShieldPartBase p = (ShieldPartBase) fittedPart.shipPart;
                shield.shieldMax += p.shieldMax;
                shield.shieldCurrent += p.shieldMax;
                shield.shieldRegen += p.shieldRegen;
            }

            if(fittedPart.shipPart instanceof GeneratorPartBase)
            {
                GeneratorPartBase p = (GeneratorPartBase) fittedPart.shipPart;
                generator.energyMax += p.energyMax;
                generator.energyCurrent += p.energyMax;
                generator.energyRegen += p.energyRegen;
            }

            if(fittedPart.shipPart instanceof EnginePartBase)
            {
                EnginePartBase p = (EnginePartBase) fittedPart.shipPart;
                engine.speedMax += p.speedMax;
                engine.accelMax += p.accelMax;
            }

        }
        
        return e;

    }
}
