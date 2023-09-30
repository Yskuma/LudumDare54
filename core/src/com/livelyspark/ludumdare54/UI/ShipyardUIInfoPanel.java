package com.livelyspark.ludumdare54.UI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.components.ships.*;
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

    public Table Generate(Skin uiSkin, Drawable background, Entity ship){

        if(!refresh){
            return table;
        }

        table = new Table();
        table.setDebug(true);

        table.add(GenerateShipStatPanel(uiSkin, background, ship)).expand().fill();
        table.row();
        table.add().expand().fill();

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
}
