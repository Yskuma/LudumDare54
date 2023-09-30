package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.ShipParts;

import javax.swing.event.ChangeEvent;

public class ShipyardUIHeader {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    private ShipParts activeButton;
    private Button engineButton;
    private Button generatorButton;
    private Button weaponsButton;
    private Button hullButton;
    private Button shieldButton;

    public ShipyardUIHeader(Skin uiSkin, Drawable background) {
        this.uiSkin = uiSkin;
        this.background = background;

        activeButton = ShipParts.Engine;

        engineButton = new Button(new Label("Engine", uiSkin), uiSkin);
        engineButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Engine;
            }
        });
        generatorButton = new Button(new Label("Generator", uiSkin), uiSkin);
        generatorButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Generator;
            }
        });
        weaponsButton = new Button(new Label("Weapons", uiSkin), uiSkin);
        weaponsButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Gun;
            }
        });
        hullButton = new Button(new Label("Hull", uiSkin), uiSkin);
        hullButton.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = ShipParts.Hull;
            }
        });
        shieldButton = new Button(new Label("Shield", uiSkin), uiSkin);
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

    public Table Generate(Skin uiSkin, Drawable background){

        if(!refresh){
            return table;
        }

        table = new Table(uiSkin);
        table.background(background);
        table.columnDefaults(0).pad(5);
        table.top().left();

        TextField nameText = new TextField("1234567", uiSkin);

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
