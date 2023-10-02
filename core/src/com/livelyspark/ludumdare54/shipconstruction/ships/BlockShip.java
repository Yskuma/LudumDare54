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

        partSlots[7][7]= false;
        partSlots[7][8]= false;
        partSlots[7][9]= false;
        partSlots[8][7]= false;
        partSlots[8][8]= false;
        partSlots[8][9]= false;

        partSlots[4][11]= false;
        partSlots[5][11]= false;
        partSlots[10][11]= false;
        partSlots[11][11]= false;

        partSlots[4][9]= false;
        partSlots[4][10]= false;
        partSlots[11][9]= false;
        partSlots[11][10]= false;

        partSlots[6][4]= false;
        partSlots[7][4]= false;
        partSlots[8][4]= false;
        partSlots[9][4]= false;

        partSlots[7][12]= true;
        partSlots[7][13]= true;
        partSlots[8][12]= true;
        partSlots[8][13]= true;

        partSlots[3][4]= true;
        partSlots[3][5]= true;
        partSlots[3][6]= true;
        partSlots[12][4]= true;
        partSlots[12][5]= true;
        partSlots[12][6]= true;
    }
}
