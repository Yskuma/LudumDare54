package com.livelyspark.ludumdare54;

import com.badlogic.ashley.core.Entity;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;

import java.util.ArrayList;

public class GlobalGameState {

    public static int stageNum = 1;
    public static int moneyEarned = 0;
    public static int moneyBanked = 0;
    public static BlockShip ship = new BlockShip();
    public static ArrayList<Entity> builtParts = new ArrayList<Entity>();

}
