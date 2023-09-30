package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ShipyardUIHeader {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    public ShipyardUIHeader(Skin uiSkin, Drawable background) {
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
        table.top().left();

        TextField nameText = new TextField("", uiSkin);

        table.add(nameText).expandX();
        table.add(new Button(new Label("Engine", uiSkin), uiSkin));
        table.add(new Button(new Label("Generator", uiSkin), uiSkin));
        table.add(new Button(new Label("Weapons", uiSkin), uiSkin));
        table.add(new Button(new Label("Hull", uiSkin), uiSkin));
        table.add(new Button(new Label("Shield", uiSkin), uiSkin));

        return table;
    }
}
