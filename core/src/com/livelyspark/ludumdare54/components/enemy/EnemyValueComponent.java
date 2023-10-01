package com.livelyspark.ludumdare54.components.enemy;

import com.badlogic.ashley.core.Component;

public class EnemyValueComponent implements Component {
    public int moneyValue;

    public EnemyValueComponent(int moneyValue)
    {
        this.moneyValue = moneyValue;
    }
}
