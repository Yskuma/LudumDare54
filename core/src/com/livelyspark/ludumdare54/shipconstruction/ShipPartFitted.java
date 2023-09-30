package com.livelyspark.ludumdare54.shipconstruction;

public class ShipPartFitted {
    public int x;
    public int y;
    public ShipPartBase shipPart;

    public ShipPartFitted(ShipPartBase shipPart, int x, int y)
    {
        this.shipPart = shipPart;
        this.x = x;
        this.y = y;
    }
}
