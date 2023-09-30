package com.livelyspark.ludumdare54.shipconstruction.parts.gun;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

import java.util.ArrayList;

public abstract class GunPartBase extends ShipPartBase {

    public float energyUsage;
    public float cooldownMax;
    public float cooldownCurrent;
    public String soundKey;

    public GunPartBase()
    {
    }

    public abstract ArrayList<Entity> Fire(Vector2 position, Vector2 baseVelocity, float direction, boolean playerShot, TextureAtlas atlas);

}
