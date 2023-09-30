package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock2;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ShipyardUIBuildMenu {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    private ScrollPane scrollPane;
    private HashMap<ShipParts, Button []> buttonLookup = new HashMap<ShipParts, Button []>();
    private BuildButton activeButton;

    public ShipyardUIBuildMenu(Skin uiSkin, Drawable background) {
        this.uiSkin = uiSkin;
        this.background = background;

        refresh = true;

        activeButton = BuildButton.None;

        populateButtons();
    }

    public ScrollPane Generate(Skin uiSkin, Drawable background, ShipParts shipPart, BuildButton activeButton){

        if(!refresh){
            return scrollPane;
        }

        table = new Table(uiSkin);
        table.background(background);
        table.top();

        switch (shipPart){

            case Engine:
                for (Button button : buttonLookup.get(ShipParts.Engine)) {
                    table.add(button);
                    table.row();
                }
                break;
            case Generator:
                for (Button button : buttonLookup.get(ShipParts.Generator)) {
                    table.add(button);
                    table.row();
                }
                break;
            case Gun:
                for (Button button : buttonLookup.get(ShipParts.Gun)) {
                    table.add(button);
                    table.row();
                }
                break;
            case Hull:
                for (Button button : buttonLookup.get(ShipParts.Hull)) {
                    table.add(button);
                    table.row();
                }
                break;
            case Shield:
                for (Button button : buttonLookup.get(ShipParts.Shield)) {
                    table.add(button);
                    table.row();
                }
                break;
        }

        table.add().expandY();

        scrollPane = new ScrollPane(table);

        return scrollPane;
    }

    private void populateButtons(){
        EnginePartBlock1 engine1 = new EnginePartBlock1();
        EnginePartBlock2 engine2 = new EnginePartBlock2();
        GeneratorPartBlock1 gen1 = new GeneratorPartBlock1();
        GeneratorPartBlock2 gen2 = new GeneratorPartBlock2();
        GunPartBlock1 gun1 = new GunPartBlock1();
        GunPartBlock2 gun2 = new GunPartBlock2();
        HullPartBlock1 hull1 = new HullPartBlock1();
        HullPartBlock2 hull2 = new HullPartBlock2();
        ShieldPartBlock1 shield1 = new ShieldPartBlock1();
        ShieldPartBlock2 shield2 = new ShieldPartBlock2();

        Button engine1button = new Button(new Label(engine1.name, uiSkin), uiSkin);
        AddButtonListener(engine1button, BuildButton.Engine1);
        Button engine2button = new Button(new Label(engine2.name, uiSkin), uiSkin);
        AddButtonListener(engine2button, BuildButton.Engine2);
        Button gen1button = new Button(new Label(gen1.name, uiSkin), uiSkin);
        AddButtonListener(gen1button, BuildButton.Generator1);
        Button gen2button = new Button(new Label(gen2.name, uiSkin), uiSkin);
        AddButtonListener(gen2button, BuildButton.Generator2);
        Button gun1button = new Button(new Label(gun1.name, uiSkin), uiSkin);
        AddButtonListener(gun1button, BuildButton.Gun1);
        Button gun2button = new Button(new Label(gun2.name, uiSkin), uiSkin);
        AddButtonListener(gun2button, BuildButton.Gun2);
        Button hull1button = new Button(new Label(hull1.name, uiSkin), uiSkin);
        AddButtonListener(hull1button, BuildButton.Hull1);
        Button hull2button = new Button(new Label(hull2.name, uiSkin), uiSkin);
        AddButtonListener(hull2button, BuildButton.Hull2);
        Button shield1button = new Button(new Label(shield1.name, uiSkin), uiSkin);
        AddButtonListener(shield1button, BuildButton.Shield1);
        Button shield2button = new Button(new Label(shield2.name, uiSkin), uiSkin);
        AddButtonListener(shield2button, BuildButton.Shield2);

        buttonLookup.put(ShipParts.Engine, new Button[]{engine1button, engine2button});
        buttonLookup.put(ShipParts.Generator, new Button[]{gen1button, gen2button});
        buttonLookup.put(ShipParts.Gun, new Button[]{gun1button, gun2button});
        buttonLookup.put(ShipParts.Hull, new Button[]{hull1button, hull2button});
        buttonLookup.put(ShipParts.Shield, new Button[]{shield1button, shield2button});
    }

    public void AddButtonListener(final Button button, final BuildButton buildButton){
        button.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = buildButton;
                for (Button [] buttons : buttonLookup.values()) {
                    for (Button b : buttons){
                        b.setChecked(false);
                    }
                }
                button.setChecked(true);
            }
        });
    }
}
