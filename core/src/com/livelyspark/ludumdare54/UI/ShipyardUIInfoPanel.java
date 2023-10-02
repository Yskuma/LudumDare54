package com.livelyspark.ludumdare54.UI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.components.ships.*;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;

public class ShipyardUIInfoPanel {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;

    public ShipyardUIInfoPanel(Skin uiSkin, Drawable background) {
        this.uiSkin = uiSkin;
        this.background = background;

        refresh = true;
    }

    public Table Generate(Skin uiSkin, Drawable background, Entity ship, ShipPartBase selectedPart){

        if(!refresh){
            return table;
        }

        table = new Table();

        table.add(GenerateShipStatPanel(uiSkin, background, ship)).top().height(300).expandX().fillX();
        table.row();
        table.add(GeneratePartStatPanel(uiSkin, background, selectedPart, ShipParts.Shield)).expand().fill();

        return table;
    }



    private Table GenerateShipStatPanel(Skin uiSkin, Drawable background, Entity ship){

        float speedMax = ship.getComponent(EngineComponent.class).speedMax;
        float accelMax = ship.getComponent(EngineComponent.class).accelMax;

        float energyMax = ship.getComponent(GeneratorComponent.class).energyMax;
        float energyRegen = ship.getComponent(GeneratorComponent.class).energyRegen;

        float hullMax = ship.getComponent(HealthComponent.class).hullMax;
        float shieldMax = ship.getComponent(HealthComponent.class).shieldMax;
        float shieldRegen = ship.getComponent(HealthComponent.class).shieldRegen;
        float shieldDelay = ship.getComponent(HealthComponent.class).shieldDelay;


        Table statTable = new Table(uiSkin);
        statTable.background(background);
        statTable.top();

        statTable.add(new Label("Speed:", uiSkin)).expandX().left();
        statTable.add(new Label(Integer.toString((int)speedMax), uiSkin)).width(60).left();
        statTable.row();
        statTable.add(new Label("Accel:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)accelMax), uiSkin)).left();
        statTable.row();
        statTable.add(new Label("Energy:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)energyMax), uiSkin)).left();
        statTable.row();
        statTable.add(new Label("E. Regen:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)energyRegen), uiSkin)).left();
        statTable.row();
        statTable.add(new Label("Hull:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)hullMax), uiSkin)).left();
        statTable.row();
        statTable.add(new Label("Shield:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)shieldMax), uiSkin)).left();
        statTable.row();
        statTable.add(new Label("S. Regen:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)shieldRegen), uiSkin)).left();
        statTable.row();
        statTable.add(new Label("S. Delay:", uiSkin)).left();
        statTable.add(new Label(Integer.toString((int)shieldDelay), uiSkin)).left();

        return statTable;
    }

    private Table GeneratePartStatPanel(Skin uiSkin, Drawable background, ShipPartBase part, ShipParts partType){

        Table partTable = new Table(uiSkin);
        partTable.background(background);

        if(part != null) {
            String partName = part.getClass().getSimpleName();

            if(part instanceof EnginePartBase)
            {
                float speedMax = ((EnginePartBase) part).speedMax;
                float accelMax = ((EnginePartBase) part).accelMax;

                partTable.add(new Label("Speed:", uiSkin)).expandX().left();
                partTable.add(new Label(Integer.toString((int) speedMax), uiSkin)).width(60).left();
                partTable.row();
                partTable.add(new Label("Accel:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) accelMax), uiSkin)).left();
                partTable.row();
            }

            if(part instanceof GeneratorPartBase)
            {
                float energyMax = ((GeneratorPartBase) part).energyMax;
                float energyRegen = ((GeneratorPartBase) part).energyRegen;

                partTable.add(new Label("Energy:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) energyMax), uiSkin)).width(60).left();
                partTable.row();
                partTable.add(new Label("E. Regen:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) energyRegen), uiSkin)).left();
                partTable.row();
            }

            if(part instanceof GunPartBase)
            {
                float energyUsage = ((GunPartBase) part).energyUsage;
                float cooldownMax = ((GunPartBase) part).cooldownMax;

                partTable.add(new Label("Energy Use:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) energyUsage), uiSkin)).width(60).left();
                partTable.row();
                partTable.add(new Label("Cooldown:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) cooldownMax), uiSkin)).left();
                partTable.row();
            }

            if(part instanceof HullPartBase)
            {
                float hullMax = ((HullPartBase) part).hullMax;

                partTable.add(new Label("Hull:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) hullMax), uiSkin)).width(60).left();
                partTable.row();
            }

            if(part instanceof ShieldPartBase)
            {
                float shieldMax = ((ShieldPartBase) part).shieldMax;
                float shieldRegen = ((ShieldPartBase) part).shieldRegen;
                float shieldDelay = ((ShieldPartBase) part).shieldDelay;

                partTable.add(new Label("Shield:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) shieldMax), uiSkin)).width(60).left();
                partTable.row();
                partTable.add(new Label("S. Regen:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) shieldRegen), uiSkin)).left();
                partTable.row();
                partTable.add(new Label("S. Delay:", uiSkin)).left();
                partTable.add(new Label(Integer.toString((int) shieldDelay), uiSkin)).left();
                partTable.row();
            }
        }

        partTable.add().colspan(2).expand().fill();


        return partTable;
    }
}
