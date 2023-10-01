package com.livelyspark.ludumdare54.components.enemy;

import com.badlogic.ashley.core.Component;

public class EnemyExploderComponent implements Component {
    public float range;
    public float damage;

    public EnemyExploderComponent(float range, float damage) {
        this.range = range;
        this.damage = damage;
    }
}
