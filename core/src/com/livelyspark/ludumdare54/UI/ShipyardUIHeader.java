package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.GlobalGameState;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;

public class ShipyardUIHeader {

    private final Drawable fieldBackground;
    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    private ShipParts activeButton;
    private ImageTextButton engineButton;
    private ImageTextButton generatorButton;
    private ImageTextButton weaponsButton;
    private ImageTextButton hullButton;
    private ImageTextButton shieldButton;

    public ShipyardUIHeader(Skin uiSkin, Drawable background, Drawable fieldBackground) {
        this.uiSkin = uiSkin;
        this.background = background;
        this.fieldBackground = fieldBackground;

        activeButton = ShipParts.Engine;

        engineButton = new ImageTextButton("Engine", uiSkin);
        engineButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Engine;
            }
        });
        generatorButton = new ImageTextButton("Generator", uiSkin);
        generatorButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Generator;
            }
        });
        weaponsButton = new ImageTextButton("Weapons", uiSkin);
        weaponsButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Gun;
            }
        });
        hullButton = new ImageTextButton("Hull", uiSkin);
        hullButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Hull;
            }
        });
        shieldButton = new ImageTextButton("Shield", uiSkin);
        shieldButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Shield;
            }
        });

        refresh = true;
    }

    public ShipParts getActiveShipPart(){
        return activeButton;
    }

    public Table Generate(Skin uiSkin, Drawable background, ShipPartBase selectedPart){

        if(!refresh){
            return table;
        }

        table = new Table(uiSkin);
        table.background(background);
        table.columnDefaults(0).pad(5);
        table.top().left();

        TextField nameText = new TextField("$" + Integer.toString(GlobalGameState.moneyBanked), uiSkin);
        if(selectedPart != null && selectedPart.cost > GlobalGameState.moneyBanked){
            nameText.setColor(new Color(0.8f, 0.1f, 0.1f, 1));
        }

        engineButton.setChecked(activeButton == ShipParts.Engine);
        generatorButton.setChecked(activeButton == ShipParts.Generator);
        weaponsButton.setChecked(activeButton == ShipParts.Gun);
        hullButton.setChecked(activeButton == ShipParts.Hull);
        shieldButton.setChecked(activeButton == ShipParts.Shield);

        table.add(nameText).expandX();
        table.add(engineButton);
        table.add(generatorButton);
        table.add(weaponsButton);
        table.add(hullButton);
        table.add(shieldButton);

        return table;
    }
}
