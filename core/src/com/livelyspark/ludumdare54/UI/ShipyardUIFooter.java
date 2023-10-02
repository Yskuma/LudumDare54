package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.Screens;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.managers.IScreenManager;

public class ShipyardUIFooter {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;

    public ShipyardUIFooter(Skin uiSkin, Drawable background) {
        this.uiSkin = uiSkin;
        this.background = background;

        refresh = true;
    }

    public Table Generate(Skin uiSkin, Drawable background, final IScreenManager screenManager){

        if(!refresh){
            return table;
        }

        table = new Table(uiSkin);
        table.background(background);
        table.columnDefaults(0).pad(5);
        table.right();

        ImageTextButton nextMission =new ImageTextButton("Next Mission", uiSkin);
        nextMission.addListener(new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                screenManager.switchScreen(Screens.Game);
            }
        });

        table.add(nextMission).right();

        return table;
    }

}
