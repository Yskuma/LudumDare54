package com.livelyspark.ludumdare54.systems.enemy;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ai.AiHoldComponent;
import com.livelyspark.ludumdare54.components.ai.AiSideStepComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;

public class EnemyAiSidestepSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> mm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<AiSideStepComponent> aim = ComponentMapper.getFor(AiSideStepComponent.class);
    private ComponentMapper<EngineComponent> em = ComponentMapper.getFor(EngineComponent.class);

    public EnemyAiSidestepSystem() {
        super(Family.all(AiSideStepComponent.class, TransformComponent.class, VelocityComponent.class, EngineComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        VelocityComponent velocity = mm.get(entity);
        EngineComponent engine = em.get(entity);
        AiSideStepComponent ai = aim.get(entity);

        if(ai.positionInitial == null)
        {
            ai.positionInitial = new Vector2(transform.position);
        }

        float currentOffset = transform.position.x - ai.positionInitial.x;

        if((ai.moveRight && currentOffset > ai.offsetMax) ||
                (!ai.moveRight && currentOffset < (ai.offsetMax * -1)))
        {
            ai.moveRight = !ai.moveRight;
        }

        velocity.x = ai.moveRight ? engine.speedMax : -engine.speedMax;

    }
}
