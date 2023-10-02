package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

    private final TextureAtlas atlas;
    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    private ScrollPane scrollPane;
    ImageTextButton removeButton;
    private HashMap<ShipParts, ArrayList<ImageTextButton>> buttonLookup = new HashMap<ShipParts, ArrayList<ImageTextButton>>();
    private BuildButton activeButton;

    public ShipyardUIBuildMenu(Skin uiSkin, Drawable background, TextureAtlas atlas) {
        this.uiSkin = uiSkin;
        this.background = background;
        this.atlas = atlas;

        refresh = true;

        activeButton = BuildButton.None;

        populateButtons();
        removeButton = new ImageTextButton("Remove", uiSkin);
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

        for (ImageTextButton button : buttonLookup.get(shipPart)) {
            table.add(button);
            table.row();
        }

        table.add(removeButton);
        table.add().expandY();

        scrollPane = new ScrollPane(table);

        return scrollPane;
    }

    private void populateButtons(){


        ArrayList<ImageTextButton> engineButtons = new ArrayList<ImageTextButton>();
        ArrayList<ImageTextButton> generatorButtons = new ArrayList<ImageTextButton>();
        ArrayList<ImageTextButton> hullButtons = new ArrayList<ImageTextButton>();
        ArrayList<ImageTextButton> shieldButtons = new ArrayList<ImageTextButton>();
        ArrayList<ImageTextButton> gunButtons = new ArrayList<ImageTextButton>();

        for (BuildButton partKey : BuildButton.values()) {
            ShipPartBase part = PartProvider.GetPart(partKey);

            if(part != null)
            {
                ImageTextButton button = new ImageTextButton(part.name, uiSkin);
                TextureRegion tr = atlas.findRegion(part.iconAtlasKey);
                ImageTextButton.ImageTextButtonStyle style = new ImageTextButton.ImageTextButtonStyle(button.getStyle());
                style.imageUp = new TextureRegionDrawable(tr);
                style.imageDown = new TextureRegionDrawable(tr);
                button.setStyle(style);
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
                for (ArrayList<ImageTextButton> buttons : buttonLookup.values()) {
                    for (ImageTextButton b : buttons){
                        b.setChecked(false);
                    }
                }
                removeButton.setChecked(false);
                button.setChecked(true);
            }
        });
    }
}
