package com.livelyspark.ludumdare54.shipconstruction;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.enemy.EnemyComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.rendering.BoundingRectangleComponent;
import com.livelyspark.ludumdare54.components.ships.EngineComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.GunCollectionComponent;
import com.livelyspark.ludumdare54.components.ships.HealthComponent;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBase;

import java.util.ArrayList;

public class ShipBase {

    public boolean[][] partSlots = new boolean[16][16];
    public ArrayList<ShipPartFitted> shipParts = new ArrayList<ShipPartFitted>();
    public String textureKey;

    public String name = "";
    public String description = "";

    public ShipBase()
    {
    }


    public int[][] GetFilledSlots() {

        //This is a fudge but if anything is outside [16][16] it's bad
        int[][] filledSlots = new int[32][32];

        for (ShipPartFitted fittedPart : shipParts) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j <= 16; j++) {

                    if(fittedPart.shipPart.usedSlots[i][j])
                    {
                        filledSlots[i+ fittedPart.x][j+ fittedPart.y]++;
                    }

                }
            }
        }

        return filledSlots;
    }

    public Entity ToEntity(float x, float y, float direction, boolean player, TextureAtlas atlas)
    {
        Entity e = new Entity();

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions(textureKey), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        e.add(new AnimationComponent(anim));
        e.add(new BoundingRectangleComponent());
        e.add(new TransformComponent(x, y, tr.getRegionWidth(),tr.getRegionHeight(), direction));
        e.add(new VelocityComponent());

        // Most ship systems have a base value
        HealthComponent health = new HealthComponent(100,0, 0, 10);
        GeneratorComponent generator = new GeneratorComponent(100, 10);
        EngineComponent engine = new EngineComponent(0, 0);

        ArrayList<GunPartBase> guns = new ArrayList<GunPartBase>();

        for (ShipPartFitted fittedPart: shipParts) {

            if(fittedPart.shipPart instanceof HullPartBase)
            {
                HullPartBase p = (HullPartBase) fittedPart.shipPart;
                health.hullMax += p.hullMax;
                health.hullCurrent += p.hullMax;
            }

            if(fittedPart.shipPart instanceof ShieldPartBase)
            {
                ShieldPartBase p = (ShieldPartBase) fittedPart.shipPart;
                health.shieldMax += p.shieldMax;
                health.shieldCurrent += p.shieldMax;
                health.shieldRegen += p.shieldRegen;
            }

            if(fittedPart.shipPart instanceof GeneratorPartBase)
            {
                GeneratorPartBase p = (GeneratorPartBase) fittedPart.shipPart;
                generator.energyMax += p.energyMax;
                generator.energyCurrent += p.energyMax;
                generator.energyRegen += p.energyRegen;
            }

            if(fittedPart.shipPart instanceof EnginePartBase)
            {
                EnginePartBase p = (EnginePartBase) fittedPart.shipPart;
                engine.speedMax += p.speedMax;
                engine.accelMax += p.accelMax;
            }

            if(fittedPart.shipPart instanceof GunPartBase)
            {
                GunPartBase p = (GunPartBase) fittedPart.shipPart;
                guns.add(p);
            }
        }

        if(!guns.isEmpty())
        {
            e.add(new GunCollectionComponent(guns, !player));
        }

        e.add(health);
        e.add(generator);
        e.add(engine);

        if(player)
        {
            e.add(new PlayerComponent());
        }
        else
        {
            e.add(new EnemyComponent());
        }
        
        return e;

    }
}
