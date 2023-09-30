package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

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

    public Table Generate(Skin uiSkin, Drawable background){

        if(!refresh){
            return table;
        }

        table = new Table(uiSkin);
        table.background(background);
        table.columnDefaults(0).pad(5);
        table.right();

        table.add(new Button(new Label("Next Mission", uiSkin), uiSkin)).right();

        return table;
    }

}
