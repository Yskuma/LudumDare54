package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ShipyardUIBuildMenu {
    public ScrollPane Generate(Skin uiSkin, Drawable background){

        Table table = new Table();

        ScrollPane scrollPane = new ScrollPane(table);

        return scrollPane;
    }

}
