package com.livelyspark.ludumdare54.components.ships;

import com.badlogic.ashley.core.Component;
import com.livelyspark.ludumdare54.shipconstruction.GunPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;

import java.util.ArrayList;

public class GunCollectionComponent implements Component {

    public boolean isTriggered;
    public ArrayList<GunPartFitted> gunParts;

    public GunCollectionComponent(ArrayList<GunPartFitted> gunParts, boolean defaultTrigger)
    {
        isTriggered = defaultTrigger;
        this.gunParts = gunParts;
    }
}
