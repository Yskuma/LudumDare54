package com.livelyspark.ludumdare54.UI;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.AtlasRegions;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;
import com.livelyspark.ludumdare54.systems.gamestages.IGameStageEvent;

import java.util.Comparator;

public class ShipyardUISystem extends EntitySystem {
    private TextureAtlas atlas;
    private ShipyardUIHeader header;
    private ShipyardUIBuildMenu buildMenu;
    private ShipyardUIShipDesigner shipDesigner;
    private ShipyardUIInfoPanel infoPanel;
    private Skin uiSkin;
    private Drawable tableBackground;
    private Table table;
    private BlockShip ship;
    private Entity eShip;
    public ShipyardUISystem(TextureAtlas atlas, BlockShip ship) {
        uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        tableBackground = uiSkin.getDrawable("textfield");

        this.atlas = atlas;
        this.ship = ship;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        eShip = ship.ToEntity(350, 350, 0, false, atlas);
        getEngine().addEntity(eShip);

        table = new Table(uiSkin);
        table.setDebug(true);
        table.top().left();

        header = new ShipyardUIHeader(uiSkin, tableBackground);
        buildMenu = new ShipyardUIBuildMenu(uiSkin, tableBackground);
        shipDesigner = new ShipyardUIShipDesigner();
        infoPanel = new ShipyardUIInfoPanel(uiSkin, tableBackground);
    }

    public void Render(Stage stage){

        table.clearChildren();

        table.add(header.Generate(uiSkin, tableBackground)).colspan(3).height(70).fill();
        table.row();
        table.add(buildMenu.Generate(uiSkin, tableBackground, ShipParts.Engine)).width(150).expandY().fill();
        table.add().bottom().left().expandX().fill();
        table.add(infoPanel.Generate(uiSkin, tableBackground, eShip)).width(200);
        table.row();
        table.add().colspan(3).height(70).fillX();
        table.setFillParent(true);

        stage.addActor(table);
    }



}