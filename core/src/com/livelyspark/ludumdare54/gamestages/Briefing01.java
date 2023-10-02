package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Briefing01 implements IBriefing {
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
                        "You are about to embark on an exciting journey aboard the" +
                                " state-of-the-art combat space ship equipped with our latest innovation -" +
                                " Transposable Efficient Tailored Resilient Interlocking Systems (or T.E.T.R.I.S.)." +
                                " This cutting-edge technology allows you to customize your ship's systems based on real-time threats," +
                                " making it one of the most versatile vessels in the galaxy!",
                        "Your mission is simple, engage in combat with some pesky low-level pirates " +
                                "who have been causing trouble in the cosmos. Each one you take down will earn you a " +
                                "handsome bounty, making this an excellent opportunity to put your new ship through " +
                                "its paces while earning some extra credits."
                };

        table.add("Mission One", "freedom48", Color.BLACK);

        for(String line : lines)
        {
            table.row();
            Label label1 = new Label(line, uiSkin);
            label1.setWrap(true);
            table.add(label1).width(600f);
        }

        stage.addActor(table);

        return stage;
    }
}
