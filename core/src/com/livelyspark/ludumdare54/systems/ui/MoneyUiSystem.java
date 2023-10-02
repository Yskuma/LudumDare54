package com.livelyspark.ludumdare54.systems.ui;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.livelyspark.ludumdare54.GlobalGameState;


public class MoneyUiSystem extends EntitySystem {

    private Stage stage;
    private Table table;

    public MoneyUiSystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        stage = new Stage();
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/blue.json"));
        Drawable tableBackground = uiSkin.getDrawable("tooltip");

        table = new Table(uiSkin);

        //table.setDebug(true);
        table.setWidth(150);
        table.setHeight(80);
        table.setX(stage.getWidth() - table.getWidth());
        table.setY(0);
        table.background(tableBackground);

        table.columnDefaults(0).center();

        stage.addActor(table);
   }

    @Override
    public void update (float deltaTime) {
        table.reset();
        table.columnDefaults(0).pad(4);

        String moneyEarnedText = "Earned: $" + GlobalGameState.moneyEarned;
        String moneyBankedText = "Banked: $" + GlobalGameState.moneyBanked;

        table.add(moneyEarnedText, "Roboto-Medium", Color.BLACK).getActor();
        table.row();
        table.add(moneyBankedText, "Roboto-Medium", Color.BLACK).getActor();
        table.row();

        stage.act();
        stage.draw();
    }



    private Drawable backgroundDrawable()
    {
        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.BLACK);
        bgPixmap.fill();
        return new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
    }

}
