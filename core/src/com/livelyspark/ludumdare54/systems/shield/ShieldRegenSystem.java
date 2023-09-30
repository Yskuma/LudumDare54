package com.livelyspark.ludumdare54.systems.shield;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;

import java.util.ArrayList;

public class ShieldRegenSystem extends IteratingSystem {

    private ComponentMapper<HealthComponent> hm = ComponentMapper.getFor(HealthComponent.class);
    public ShieldRegenSystem(){
        super(Family.all(HealthComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent hc = hm.get(entity);
        hc.shieldCurrent = Math.min(hc.shieldMax, hc.shieldCurrent + (deltaTime * hc.shieldRegen));
    }

}
