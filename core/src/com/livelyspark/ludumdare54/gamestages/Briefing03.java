package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Briefing03 implements IBriefing {
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
        table.setWidth(668);
        table.setHeight(668);
        table.setX((Gdx.graphics.getWidth() / 2) - (668 / 2));
        table.setY((Gdx.graphics.getHeight() / 2) - (668 / 2));
        table.background(tableBackground);

        table.columnDefaults(0).center();

        String[] lines = new String[]
                {
                        "Greetings, brave pilot!",
                        "Your mission is one that requires not only skill but also courage." +
                                " You are about to embark on a journey into the heart of darkness itself," +
                                " a volcanic planet teeming with mercenaries who have been trained by some of the most ruthless tacticians in the galaxy.",
                        "Your objective is simple: Destroy them all. However, this is easier said than done." +
                                " These pilots are seasoned veterans, each one more deadly than the last." +
                                " Their weapons fire like lightning from the heavens, striking down anything that dares to cross their path.",
                        "But fear not, intrepid warrior! For you have been chosen for this task because you possess the skills necessary to overcome such adversity." +
                                " In your hands rests a weapon of unparalleled power," +
                                " it is up to you now to wield it wisely and bring peace back to this troubled world.",
                        "Press SPACE to continue."
                };

        table.add("Mission Three", "freedom48", Color.BLACK).padBottom(32f);

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
