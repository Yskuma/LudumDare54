package com.livelyspark.ludumdare54.shipconstruction.parts.weapon;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

public abstract class WeaponPartBase extends ShipPartBase {

    public float powerMax;
    public float powerRegen;

    public WeaponPartBase()
    {
    }

    public abstract void Fire(int x, int y, int direction, TextureAtlas atlas);


}
