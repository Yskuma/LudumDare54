package com.livelyspark.ludumdare54.shipconstruction.ships;

import com.livelyspark.ludumdare54.keys.AtlasKeys;
import com.livelyspark.ludumdare54.shipconstruction.ShipBase;

public class BlockShip extends ShipBase {


    public BlockShip()
    {
        textureKey = AtlasKeys.Ship_Player_001;

        for(int i = 4; i <= 11; i++)
        {
            for(int j = 4; j <= 11; j++)
            {
                partSlots[i][j] = true;
            }
        }

        partSlots[6][6]= false;
    }
}
