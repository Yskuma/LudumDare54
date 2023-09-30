package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.ShipParts;

public class ShipyardUIBuildMenu {

    private Skin uiSkin;
    private Drawable background;
    private Table table;
    private Boolean refresh;
    private ScrollPane scrollPane;

    public ShipyardUIBuildMenu(Skin uiSkin, Drawable background) {
        this.uiSkin = uiSkin;
        this.background = background;

        refresh = true;
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
                table.add(new Button(new Label("Engine One", uiSkin), uiSkin));
                table.row();
                table.add(new Button(new Label("Engine Two", uiSkin), uiSkin));
                table.row();
                break;
            case Generator:
                table.add(new Button(new Label("Generator One", uiSkin), uiSkin));
                table.row();
                table.add(new Button(new Label("Generator Two", uiSkin), uiSkin));
                table.row();
                break;
            case Gun:
                break;
            case Hull:
                break;
            case Shield:
                break;
        }

        table.add().expandY();

        scrollPane = new ScrollPane(table);

        return scrollPane;
    }
}
