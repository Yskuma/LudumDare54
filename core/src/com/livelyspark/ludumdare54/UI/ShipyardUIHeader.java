package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ShipyardUIHeader {
    public Table Generate(Skin uiSkin, Drawable background){
        Table table = new Table(uiSkin);
        table.background(background);
        table.columnDefaults(0).pad(5);
        table.top().left();


        Label nameLabel = new Label("Money: ", uiSkin);
        TextField nameText = new TextField("", uiSkin);

        table.add(nameLabel);
        table.add(nameText);
        table.add().expandX();

        return table;
    }
}
