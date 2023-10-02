package com.livelyspark.ludumdare54.gamestages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
        table.setWidth(668);
        table.setHeight(668);
        table.setX((Gdx.graphics.getWidth() / 2) - (668 / 2));
        table.setY((Gdx.graphics.getHeight() / 2) - (668 / 2));
        table.background(tableBackground);

        table.columnDefaults(0).center();

        String[] lines = new String[]
                {
                        "Greetings, brave pilot!",
                        "Greetings, brave pilot! You are about to embark on a thrilling mission that will test your skills and courage. " +
                                "Your objective is simple - navigate through the treacherous asteroid field with precision and speed. " +
                                "However, there's more than just rocks in this cosmic maze.",
                        "As you dodge these celestial boulders, keep an eye out for enemy mines hidden among them. " +
                                "They're not as big as asteroids but they pack quite a punch if you happen to collide with one. " +
                                "But fear not, for each asteroid you destroy, you'll earn extra credits. " +
                                "So, aim straight and fire away!",
                        "Press SPACE to continue."
                };

        table.add("Mission Two", "freedom48", Color.BLACK).padBottom(32f);

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
