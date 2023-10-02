package com.livelyspark.ludumdare54.components.player;

import com.badlogic.ashley.core.Component;

public class PlayerProjectileComponent implements Component {
    public float damage;

    public PlayerProjectileComponent(float damage)
    {
        this.damage = damage;
    }
}
