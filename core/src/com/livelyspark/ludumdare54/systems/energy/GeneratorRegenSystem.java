package com.livelyspark.ludumdare54.systems.energy;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;

import java.util.ArrayList;

public class GeneratorRegenSystem extends IteratingSystem {

    private ComponentMapper<GeneratorComponent> gm = ComponentMapper.getFor(GeneratorComponent.class);

    public GeneratorRegenSystem(){
        super(Family.all(GeneratorComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        GeneratorComponent gc = gm.get(entity);
        gc.energyCurrent = Math.min(gc.energyMax, gc.energyCurrent + (deltaTime * gc.energyRegen));
    }

}
