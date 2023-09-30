package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

import java.util.ArrayList;

public abstract class GunPartBase extends ShipPartBase {

    public float energyUsage;
    public float cooldownMax;
    public float cooldownCurrent;

    public GunPartBase()
    {
    }

    public abstract ArrayList<Entity> Fire(int x, int y, float direction, boolean playerShot, TextureAtlas atlas);

}
