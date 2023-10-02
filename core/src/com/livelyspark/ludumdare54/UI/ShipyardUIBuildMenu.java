package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBase;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBase;
import com.livelyspark.ludumdare54.utility.PartProvider;

import java.util.ArrayList;
import java.util.HashMap;

public class ShipyardUIBuildMenu {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    private ScrollPane scrollPane;
    Button removeButton;
    private HashMap<ShipParts, ArrayList<Button>> buttonLookup = new HashMap<ShipParts, ArrayList<Button>>();
    private BuildButton activeButton;

    public ShipyardUIBuildMenu(Skin uiSkin, Drawable background) {
        this.uiSkin = uiSkin;
        this.background = background;

        refresh = true;

        activeButton = BuildButton.None;

        populateButtons();
        removeButton = new Button(new Label("Remove", uiSkin), uiSkin);
        AddButtonListener(removeButton, BuildButton.Remove);
    }

    public BuildButton getActiveButton(){
        return activeButton;
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
        table.add(removeButton);
        table.add().expandY();


        scrollPane = new ScrollPane(table);

        return scrollPane;
    }

    private void populateButtons(){


        ArrayList<Button> engineButtons = new ArrayList<Button>();
        ArrayList<Button> generatorButtons = new ArrayList<Button>();
        ArrayList<Button> hullButtons = new ArrayList<Button>();
        ArrayList<Button> shieldButtons = new ArrayList<Button>();
        ArrayList<Button> gunButtons = new ArrayList<Button>();

        for (BuildButton partKey : BuildButton.values()) {
            ShipPartBase part = PartProvider.GetPart(partKey);

            if(part != null)
            {
                Button button = new Button(new Label(part.name, uiSkin), uiSkin);
                AddButtonListener(button, partKey);

                if(part instanceof EnginePartBase){engineButtons.add(button);}
                if(part instanceof GeneratorPartBase){generatorButtons.add(button);}
                if(part instanceof HullPartBase){hullButtons.add(button);}
                if(part instanceof ShieldPartBase){shieldButtons.add(button);}
                if(part instanceof GunPartBase){gunButtons.add(button);}
            }
        }

        buttonLookup.put(ShipParts.Engine, engineButtons);
        buttonLookup.put(ShipParts.Generator, generatorButtons);
        buttonLookup.put(ShipParts.Gun, gunButtons);
        buttonLookup.put(ShipParts.Hull, hullButtons);
        buttonLookup.put(ShipParts.Shield, shieldButtons);
    }

    public void AddButtonListener(final Button button, final BuildButton buildButton){
        button.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                activeButton = buildButton;
                for (ArrayList<Button> buttons : buttonLookup.values()) {
                    for (Button b : buttons){
                        b.setChecked(false);
                    }
                }
                removeButton.setChecked(false);
                button.setChecked(true);
            }
        });
    }
}
