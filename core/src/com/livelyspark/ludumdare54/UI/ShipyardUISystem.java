package com.livelyspark.ludumdare54.UI;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.livelyspark.ludumdare54.GlobalGameState;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.rendering.AnimationComponent;
import com.livelyspark.ludumdare54.components.shipyard.ShipPartComponent;
import com.livelyspark.ludumdare54.components.shipyard.ValidPartComponent;
import com.livelyspark.ludumdare54.enums.BuildButton;
import com.livelyspark.ludumdare54.enums.RenderLayers;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartBase;
import com.livelyspark.ludumdare54.shipconstruction.ShipPartFitted;
import com.livelyspark.ludumdare54.shipconstruction.ships.BlockShip;
import com.livelyspark.ludumdare54.utility.PartProvider;

import java.util.ArrayList;

public class ShipyardUISystem extends EntitySystem {
    private final Viewport viewport;
    private final Camera camera;
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
    private ArrayList<Entity> ghostParts;
    private ArrayList<Entity> builtParts;
    private ShipPartBase selectedPart;
    private Entity validPart;
    private IScreenManager screenManager;

    public ShipyardUISystem(Stage stage, TextureAtlas atlas, IScreenManager screenManager, Viewport viewport, Camera camera) {
        uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        tableBackground = uiSkin.getDrawable("textfield");

        this.screenManager = screenManager;
        this.viewport = viewport;
        this.camera = camera;

        this.atlas = atlas;
        this.ship = GlobalGameState.ship;
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
                    int x = (i*8) + (256/2) - 64 + 4;
                    int y = (j*8) + (256/2) - 64 + 4;
                    Entity e = new Entity();
                    e.add(new AnimationComponent(anim));
                    TransformComponent trans = new TransformComponent(x, y, 8,8, 0.0f);
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
        table.add(infoPanel.Generate(uiSkin, tableBackground, eShip, selectedPart)).width(200).expandY().fill().top();
        table.row();
        table.add(footer.Generate(uiSkin,tableBackground, screenManager)).colspan(3).height(70).expandX().fillX();
        table.setFillParent(true);

        stage.addActor(table);

        updateGhostPart();
        AddPartUpdate();
        RemovePartUpdate();

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

        if(activeBuildButton != BuildButton.None
        && activeBuildButton != BuildButton.Remove){

            Vector2 mouseWorld = getMouseWorldPos();

            for(int i = 0; i <= 15; i++)
            {
                for(int j = 0; j <= 15; j++)
                {
                    if(selectedPart.usedSlots[i][j]){
                        Entity e = new Entity();
                        Vector2 checkWorldPos = new Vector2(mouseWorld.x + (i * 8), mouseWorld.y + (j * 8));

                        e.add(new TransformComponent(checkWorldPos.x,checkWorldPos.y,8,8,0));
                        Animation<TextureRegion> anim;
                        if(IsEmptyPartFromPos((int)checkWorldPos.x, (int)checkWorldPos.y)){
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
                Vector2 gridPos = worldPosToGrid(getMouseWorldPos());
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
        selectedPart = PartProvider.GetPart(activeBuildButton);
    }

    private Boolean IsEmptyPartFromPos(int x, int y){
        Vector2 gridPos = worldPosToGrid(new Vector2(x,y));

        if(gridPos.x >= 0 && gridPos.x <= 15
        && gridPos.y >= 0 && gridPos.y <= 15){

            boolean IsFilled = ship.GetFilledSlots()[(int)gridPos.x][(int)gridPos.y] > 0;

            return ship.partSlots[(int)gridPos.x][(int)gridPos.y]
                    && !IsFilled;
        }

        return false;
    }

    private Vector2 getMouseScreenPos()
    {
        return new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }

    private Vector2 getMouseWorldPos()
    {
        Vector2 screenPos = getMouseScreenPos();
        Vector3 worldPos =  camera.unproject(new Vector3(screenPos.x, screenPos.y, 0));
        return new Vector2(worldPos.x, worldPos.y);
    }

    private Vector2 worldPosToGrid(Vector2 worldPos){

        int gridX = ((int)worldPos.x + 128) / 8;
        int gridY = ((int)worldPos.y + 128) / 8;

        return new Vector2(gridX, gridY);
    }

    private Vector2 gridPosToWorldPos(Vector2 gridPos){

        int realX = ((int)gridPos.x * 8) - 128 + 4;
        int realY = ((int)gridPos.y * 8) - 128 + 4;

        return new Vector2(realX, realY);
    }

    private void UpdateShipEntity(){
        eShip = ship.ToEntity(256/2, 256/2, 0, false, atlas);
        eShip.getComponent(TransformComponent.class).size.x = 128;
        eShip.getComponent(TransformComponent.class).size.y = 128;
        eShip.getComponent(TransformComponent.class).renderLayer = RenderLayers.Background;
    }

    private void AddPartUpdate() {
        ValidPartComponent val = validPart.getComponent(ValidPartComponent.class);

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                && val.IsValid) {
            ShipPartBase part;
            Array<TextureAtlas.AtlasRegion> ar = null;
            Animation<TextureRegion> animation = null;

            part = PartProvider.GetPart(val.Part);

            if (part == null) {
                return;
            }
            ar = atlas.findRegions(part.iconAtlasKey);
            animation = new Animation<TextureRegion>(0.033f, ar, Animation.PlayMode.LOOP);

            ShipPartFitted partFitted = new ShipPartFitted(part, val.OriginX, val.OriginY);
            ship.shipParts.add(partFitted);
            Entity newPart = new Entity();
            ShipPartComponent partComponent = new ShipPartComponent();
            partComponent.Part = part;
            partComponent.OriginX = val.OriginX;
            partComponent.OriginY = val.OriginY;
            partComponent.PartFitted = partFitted;
            newPart.add(partComponent);

            if(animation != null){
                newPart.add(new AnimationComponent(animation));
                Vector2 pos = gridPosToWorldPos(new Vector2(val.OriginX, val.OriginY));
                int width = ar.first().getRegionWidth() * 2;
                int height = ar.first().getRegionHeight() * 2;
                TransformComponent tc = new TransformComponent(pos.x + (width/2),pos.y + (height/2), width,height, 0);
                tc.renderLayer = RenderLayers.Foreground;
                newPart.add(tc);
            }

            getEngine().addEntity(newPart);
            builtParts.add(newPart);
        }
    }

    private void RemovePartUpdate() {
          if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)
          && activeBuildButton == BuildButton.Remove){
              Vector2 gridPos = worldPosToGrid(getMouseWorldPos());
              int clickX = (int)gridPos.x;
              int clickY = (int)gridPos.y;

              Entity part = null;
              for(Entity e : builtParts ){
                  ShipPartComponent partComponent = e.getComponent(ShipPartComponent.class);
                  int partX = partComponent.OriginX - clickX;
                  int partY = partComponent.OriginY - clickY;

                  if(partX >= 0 && partX <= 15 && partY >= 0 && partY <= 15) {
                    if(partComponent.Part.usedSlots[partX][partY]){
                        part = e;
                        break;
                    }
                  }
              }

              if(part == null){
                  return;
              }

              ShipPartComponent partComponent = part.getComponent(ShipPartComponent.class);

              builtParts.remove(part);
              ship.shipParts.remove(partComponent.PartFitted);
              getEngine().removeEntity(part);
        }
    }


}
