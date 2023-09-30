package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class ShipyardUI {

    private ShipyardUIHeader header;
    public ShipyardUI() {
        header = new ShipyardUIHeader();
    }

    public void Show(Stage stage){
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        Drawable tableBackground = uiSkin.getDrawable("textfield");
        Table table = new Table(uiSkin);
        table.setDebug(true);

        table.top().left();


        table.add(header.Generate(uiSkin, tableBackground)).colspan(3).height(60).fillX();
        table.row();
        table.add().width(150).expandY();
        table.add().expandX();
        table.add().width(150);
        table.row();
        table.add().colspan(3).height(60).fillX();
        table.setFillParent(true);

        stage.addActor(table);
    }

}
