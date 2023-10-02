package com.livelyspark.ludumdare54.shipconstruction;

public abstract class ShipPartBase {

    public boolean[][] usedSlots = new boolean[16][16];
    public String name = "";
    public String description = "";
    public int cost = 0;

    public String iconAtlasKey = "";

    public ShipPartBase()
    {
    }
}
