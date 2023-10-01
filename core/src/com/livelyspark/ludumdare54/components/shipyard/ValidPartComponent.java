package com.livelyspark.ludumdare54.components.shipyard;

import com.badlogic.ashley.core.Component;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.ShipParts;

public class ValidPartComponent implements Component {
    public BuildButton Part;
    public int OriginX;
    public int OriginY;
    public boolean IsValid;
}
