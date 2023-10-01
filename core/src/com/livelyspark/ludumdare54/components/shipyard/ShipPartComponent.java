package com.livelyspark.ludumdare54.components.shipyard;

import com.badlogic.ashley.core.Component;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;

public class ShipPartComponent implements Component {
    public ShipPartBase Part;
    public ShipPartFitted PartFitted;
    public int OriginX;
    public int OriginY;
}
