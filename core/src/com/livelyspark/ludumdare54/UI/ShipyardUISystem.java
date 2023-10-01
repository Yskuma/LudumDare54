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
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.RenderLayers;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;

import java.util.Comparator;

public class ShipyardUISystem extends EntitySystem {
    private TextureAtlas atlas;
    private ShipyardUIHeader header;
    private ShipyardUIBuildMenu buildMenu;
    private ShipyardUIShipDesigner shipDesigner;
    private ShipyardUIInfoPanel infoPanel;
    private ShipyardUIFooter footer;
    private Skin uiSkin;
    private Drawable tableBackground;
    private Table table;
    private BlockShip ship;
    private Entity eShip;
    private Stage stage;
    private BuildButton activeBuildButton;
    public ShipyardUISystem(Stage stage, TextureAtlas atlas, BlockShip ship) {
        uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        tableBackground = uiSkin.getDrawable("textfield");

        this.atlas = atlas;
        this.ship = ship;
        this.stage = stage;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        eShip = ship.ToEntity(768/2 - 160, 768/2 - 160, 0, false, atlas);
        eShip.getComponent(TransformComponent.class).size.x = 320;
        eShip.getComponent(TransformComponent.class).size.y = 320;
        eShip.getComponent(TransformComponent.class).renderLayer = RenderLayers.Background;
        getEngine().addEntity(eShip);

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions("ship-part-empty"), Animation.PlayMode.LOOP);
        TextureRegion tr = anim.getKeyFrame(0.0f);

        for(int i = 0; i <= 15; i++)
        {
            for(int j = 0; j <= 15; j++)
            {
                if(ship.partSlots[i][j]){
                    int x = (i*20) + (768/2) - 160;
                    int y = (j*20) + (768/2) - 160;;
                    Entity e = new Entity();
                    e.add(new AnimationComponent(anim));
                    TransformComponent trans = new TransformComponent(x, y, 20,20, 0.0f);
                    e.add(trans);
                    getEngine().addEntity(e);
                }
            }
        }

        table = new Table(uiSkin);
        table.top().left();

        header = new ShipyardUIHeader(uiSkin, tableBackground);
        buildMenu = new ShipyardUIBuildMenu(uiSkin, tableBackground);
        shipDesigner = new ShipyardUIShipDesigner();
        infoPanel = new ShipyardUIInfoPanel(uiSkin, tableBackground);
        footer = new ShipyardUIFooter(uiSkin, tableBackground);
    }

    @Override
    public void update (float deltaTime) {
        table.clearChildren();

        table.add(header.Generate(uiSkin, tableBackground)).colspan(3).height(70).fill();
        table.row();
        table.add(buildMenu.Generate(uiSkin, tableBackground, header.getActiveShipPart(), activeBuildButton)).width(200).expandY().fill();
        table.add().bottom().left().expandX().fill();
        table.add(infoPanel.Generate(uiSkin, tableBackground, eShip)).width(200).expandY().fill().top();
        table.row();
        table.add(footer.Generate(uiSkin,tableBackground)).colspan(3).height(70).expandX().fillX();
        table.setFillParent(true);

        stage.addActor(table);

        stage.act();
        stage.draw();
    }



}
