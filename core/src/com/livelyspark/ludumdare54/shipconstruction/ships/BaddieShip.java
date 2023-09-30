package com.livelyspark.ludumdare54.shipconstruction.ships;

import com.livelyspark.ludumdare54.enums.AtlasRegions;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;

public class BaddieShip extends ShipBase {


    public BaddieShip()
    {
        textureKey = AtlasRegions.Ship002;

        for(int i = 4; i <= 11; i++)
        {
            for(int j = 4; j <= 11; j++)
            {
                partSlots[i][j] = true;
            }
        }
    }
}
