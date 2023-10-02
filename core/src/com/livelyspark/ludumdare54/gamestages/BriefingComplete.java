package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class BriefingComplete implements IBriefing {
    @Override
    public String GetBackground() {
        return "briefing_screen.png";
    }

    @Override
    public Stage GetStage() {
        Stage stage = new Stage();
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));

        Drawable tableBackground = uiSkin.getDrawable("tooltip");

        Table table = new Table(uiSkin);
        table.columnDefaults(0).pad(5);
        //table.setDebug(true);
        table.setWidth(668);
        table.setHeight(668);
        table.setX((Gdx.graphics.getWidth() / 2) - (668 / 2));
        table.setY((Gdx.graphics.getHeight() / 2) - (668 / 2));
        table.background(tableBackground);

        table.columnDefaults(0).center();

        String[] lines = new String[]
                {
                        "Greetings, brave pilot!",
                        "There are no more missions, but here, have a carrot.",
                        "If you press Space now you're going back to Mission One but I'll let you keep your ship.",
                        "Have fun!"
                };

        table.add("Further Missions?", "freedom48", Color.BLACK).padBottom(32f);

        for(String line : lines)
        {
            table.row();
            Label label1 = new Label(line, uiSkin);
            label1.setWrap(true);
            table.add(label1).width(600f).padBottom(16f);
        }

        stage.addActor(table);

        return stage;
    }
}
