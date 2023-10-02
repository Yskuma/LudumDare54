package com.livelyspark.ludumdare54.systems.enemy;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ai.AiChargeFastComponent;
import com.livelyspark.ludumdare54.components.ai.AiHoldComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;

public class EnemyAiChargeFastSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> mm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<EngineComponent> em = ComponentMapper.getFor(EngineComponent.class);

    public EnemyAiChargeFastSystem() {
        super(Family.all(AiChargeFastComponent.class, TransformComponent.class, VelocityComponent.class, EngineComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        VelocityComponent velocity = mm.get(entity);
        EngineComponent engine = em.get(entity);

        velocity.y = -engine.speedMax;
    }
}
