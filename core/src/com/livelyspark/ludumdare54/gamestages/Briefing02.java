package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Briefing02 implements IBriefing {
    @Override
    public String GetBackground() {
        return "briefing_screen.png";
    }

    @Override
    public Stage GetStage() {
        Stage stage = new Stage();
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/blue.json"));

        Drawable tableBackground = uiSkin.getDrawable("tooltip");

        Table table = new Table(uiSkin);
        table.columnDefaults(0).pad(5);
        //table.setDebug(true);
        table.setWidth(450);
        table.setHeight(300);
        table.setX((Gdx.graphics.getWidth()/2) - 225);
        table.setY((Gdx.graphics.getHeight()/2) - 150);
        table.background(tableBackground);

        table.columnDefaults(0).center();

        table.add("Briefing 02", "medium", Color.BLACK);
        table.row();
        table.add("The second mission.", "medium", Color.BLACK);

        stage.addActor(table);

        return stage;
    }
}
