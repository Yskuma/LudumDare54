package com.livelyspark.ludumdare54.systems.enemy;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.ai.AiMoveAndHoldComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;

public class EnemyAiMoveAndHoldSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> mm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<AiMoveAndHoldComponent> aim = ComponentMapper.getFor(AiMoveAndHoldComponent.class);
    private ComponentMapper<EngineComponent> em = ComponentMapper.getFor(EngineComponent.class);

    public EnemyAiMoveAndHoldSystem() {
        super(Family.all(AiMoveAndHoldComponent.class, TransformComponent.class, VelocityComponent.class, EngineComponent.class).get());
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        TransformComponent transform = tm.get(entity);
        VelocityComponent velocity = mm.get(entity);
        EngineComponent engine = em.get(entity);
        AiMoveAndHoldComponent ai = aim.get(entity);

        if(ai.positionTarget == null)
        {
            ai.positionTarget = new Vector2(transform.position).add(ai.positionOffset);
        }

        Vector2 dir = new Vector2(ai.positionTarget).sub(new Vector2(transform.position)).nor().scl(engine.speedMax);
        velocity.x = dir.x;
        velocity.y = dir.y;

        ai.positionTarget.y = ai.positionTarget.y + (StaticConstants.camSpeed * deltaTime);
    }
}
