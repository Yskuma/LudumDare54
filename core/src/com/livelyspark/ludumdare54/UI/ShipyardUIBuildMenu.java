package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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

    public ScrollPane Generate(Skin uiSkin, Drawable background, ShipParts shipPart){

        if(!refresh){
            return scrollPane;
        }

        table = new Table(uiSkin);
        table.background(background);
        table.top();

        table.add(new Button(new Label("Part One", uiSkin), uiSkin));
        table.row();
        table.add(new Button(new Label("Part Two", uiSkin), uiSkin));
        table.row();
        table.add().expandY();

        scrollPane = new ScrollPane(table);

        return scrollPane;
    }

}
