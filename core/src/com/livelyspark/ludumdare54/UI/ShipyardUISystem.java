package com.livelyspark.ludumdare54.UI;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.shipyard.ShipPartComponent;
import com.livelyspark.ludumdare54.components.shipyard.ValidPartComponent;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.RenderLayers;
import com.livelyspark.ludumdare54.enums.ShipParts;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.engine.EnginePartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.generator.GeneratorPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.gun.GunPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.hull.HullPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock1;
import com.livelyspark.ludumdare54.shipconstruction.parts.shield.ShieldPartBlock2;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ShipyardUISystem extends EntitySystem {
    private TextureAtlas atlas;
    private ShipyardUIHeader header;
    private ShipyardUIBuildMenu buildMenu;
    private ShipyardUIShipDesigner shipDesigner;
    private ShipyardUIInfoPanel infoPanel;
    private ShipyardUIFooter footer;
    private ShipyardUIPartAdd partAdd;
    private Skin uiSkin;
    private Drawable tableBackground;
    private Table table;
    private BlockShip ship;
    private Entity eShip;
    private Stage stage;
    private BuildButton activeBuildButton;
    private ArrayList<Entity> ghostParts;
    private ArrayList<Entity> builtParts;
    private ShipPartBase selectedPart;
    private Entity validPart;

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

        UpdateShipEntity();
        getEngine().addEntity(eShip);

        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.033f, atlas.findRegions("ship-part-empty"), Animation.PlayMode.LOOP);

        for(int i = 0; i <= 15; i++)
        {
            for(int j = 0; j <= 15; j++)
            {
                if(ship.partSlots[i][j]){
                    int x = (i*20) + (768/2) - 150;
                    int y = (j*20) + (768/2) - 150;;
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
        partAdd = new ShipyardUIPartAdd();

        ghostParts = new ArrayList<Entity>();
        validPart = new Entity();
        ValidPartComponent val = new ValidPartComponent();
        val.IsValid = false;
        validPart.add(val);
        getEngine().addEntity(validPart);
        builtParts = new ArrayList<>();
    }

    @Override
    public void update (float deltaTime) {
        activeBuildButton = buildMenu.getActiveButton();
        UpdateShipEntity();

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

        updateGhostPart();
        AddPartUpdate();

        stage.act();
        stage.draw();
    }

    public void updateGhostPart(){
        updateSelectedPart();

        for(Entity part : ghostParts){
            getEngine().removeEntity(part);
        }
        ghostParts = new ArrayList<>();

        Boolean valid = true;

        if(activeBuildButton != BuildButton.None){

            int mouseX = (((Gdx.input.getX() + 14) /20) * 20) -6;
            int mouseY = (((Gdx.graphics.getHeight() - (Gdx.input.getY() + 6))/20) * 20) -6;
            for(int i = 0; i <= 15; i++)
            {
                for(int j = 0; j <= 15; j++)
                {
                    if(selectedPart.usedSlots[i][j]){
                        Entity e = new Entity();
                        e.add(new TransformComponent(i*20 + mouseX,j*20 + mouseY,20,20,0));
                        Animation<TextureRegion> anim;
                        if(IsEmptyPartFromPos(i*20 + mouseX, j*20 + mouseY)){
                            anim = new Animation<TextureRegion>(0.033f, atlas.findRegions("green-square"), Animation.PlayMode.LOOP);
                        }
                        else{
                            anim = new Animation<TextureRegion>(0.033f, atlas.findRegions("red-square"), Animation.PlayMode.LOOP);
                            valid = false;
                        }
                        e.add(new AnimationComponent(anim));
                        ghostParts.add(e);
                    }
                }
            }
            for(Entity part : ghostParts){
                getEngine().addEntity(part);
            }
            if(valid){
                Vector2 gridPos = MousePosToGridPos(mouseX, mouseY);
                validPart.getComponent(ValidPartComponent.class).Part = activeBuildButton;
                validPart.getComponent(ValidPartComponent.class).OriginX = (int)gridPos.x;
                validPart.getComponent(ValidPartComponent.class).OriginY = (int)gridPos.y;
                validPart.getComponent(ValidPartComponent.class).IsValid = true;
            }
            else{
                validPart.getComponent(ValidPartComponent.class).IsValid = false;
            }
        }
    }

    private void updateSelectedPart(){
        if(activeBuildButton == BuildButton.None){
            selectedPart = null;
            return;
        }

        switch (activeBuildButton){
            case Engine1:
                selectedPart = new EnginePartBlock1();
                break;
            case Engine2:
                selectedPart = new EnginePartBlock2();
                break;
            case Generator1:
                selectedPart = new GeneratorPartBlock1();
                break;
            case Generator2:
                selectedPart = new GeneratorPartBlock2();
                break;
            case Gun1:
                selectedPart = new GunPartBlock1();
                break;
            case Gun2:
                selectedPart = new GunPartBlock2();
                break;
            case Hull1:
                selectedPart = new HullPartBlock1();
                break;
            case Hull2:
                selectedPart = new HullPartBlock2();
                break;
            case Shield1:
                selectedPart = new ShieldPartBlock1();
                break;
            case Shield2:
                selectedPart = new ShieldPartBlock2();
                break;
        }
    }

    private Boolean IsEmptyPartFromPos(int x, int y){
        Vector2 gridPos = MousePosToGridPos(x,y);

        if(gridPos.x >= 0 && gridPos.x <= 15
        && gridPos.y >= 0 && gridPos.y <= 15){

            boolean IsFilled = ship.GetFilledSlots()[(int)gridPos.x][(int)gridPos.y] > 0;

            return ship.partSlots[(int)gridPos.x][(int)gridPos.y]
                    && !IsFilled;
        }

        return false;
    }

    private Vector2 MousePosToGridPos(int x, int y){
        int gridX = x/20 - 11;
        int gridY = y/20 - 11;

        return new Vector2(gridX, gridY);
    }

    private void UpdateShipEntity(){
        eShip = ship.ToEntity(768/2, 768/2, 0, false, atlas);
        eShip.getComponent(TransformComponent.class).size.x = 320;
        eShip.getComponent(TransformComponent.class).size.y = 320;
        eShip.getComponent(TransformComponent.class).renderLayer = RenderLayers.Background;
    }

    private void AddPartUpdate() {
        ValidPartComponent val = validPart.getComponent(ValidPartComponent.class);

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                && val.IsValid) {
            ShipPartBase part;
            switch (val.Part) {
                case Engine1:
                    part = new EnginePartBlock1();
                    break;
                case Engine2:
                    part = new EnginePartBlock2();
                    break;
                case Generator1:
                    part = new GeneratorPartBlock1();
                    break;
                case Generator2:
                    part = new GeneratorPartBlock2();
                    break;
                case Gun1:
                    part = new GunPartBlock1();
                    break;
                case Gun2:
                    part = new GunPartBlock2();
                    break;
                case Hull1:
                    part = new HullPartBlock1();
                    break;
                case Hull2:
                    part = new HullPartBlock2();
                    break;
                case Shield1:
                    part = new ShieldPartBlock1();
                    break;
                case Shield2:
                    part = new ShieldPartBlock2();
                    break;
                default:
                    return;
            }
            ShipPartFitted partFitted = new ShipPartFitted(part, val.OriginX, val.OriginY);
            ship.shipParts.add(partFitted);
            Entity newPart = new Entity();
            ShipPartComponent partComponent = new ShipPartComponent();
            partComponent.Part = part;
            partComponent.OriginX = val.OriginX;
            partComponent.OriginY = val.OriginY;
            newPart.add(partComponent);
            getEngine().addEntity(newPart);
            builtParts.add(newPart);
        }
    }
}
