package com.livelyspark.ludumdare54.systems.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
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
import com.livelyspark.ludumdare54.ashley.IteratingSystemBetter;
import com.livelyspark.ludumdare54.components.DebugLabelComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import com.livelyspark.ludumdare54.components.player.PlayerComponent;
import com.livelyspark.ludumdare54.components.ships.GeneratorComponent;
import com.livelyspark.ludumdare54.components.ships.HullComponent;
import com.livelyspark.ludumdare54.components.ships.ShieldComponent;
import text.formic.Stringf;


public class DebugPlayerDetailUiSystem extends IteratingSystemBetter {


    private Stage stage;
    private Table table;

    private ComponentMapper<DebugLabelComponent> dm = ComponentMapper.getFor(DebugLabelComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<VelocityComponent> mm = ComponentMapper.getFor(VelocityComponent.class);
    private ComponentMapper<HullComponent> hm = ComponentMapper.getFor(HullComponent.class);
    private ComponentMapper<ShieldComponent> sm = ComponentMapper.getFor(ShieldComponent.class);
    private ComponentMapper<GeneratorComponent> gm = ComponentMapper.getFor(GeneratorComponent.class);

    public DebugPlayerDetailUiSystem() {
        super(Family.all(PlayerComponent.class,
                TransformComponent.class,
                VelocityComponent.class,
                HullComponent.class,
                ShieldComponent.class,
                GeneratorComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        stage = new Stage();
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        Drawable tableBackground = uiSkin.getDrawable("textfield");


        table = new Table(uiSkin);

        //table.setDebug(true);
        table.setWidth(160);
        table.setHeight(75);
        table.setX(0);
        table.setY(stage.getHeight() - table.getHeight());
        table.background(tableBackground);

        table.columnDefaults(0).center();

        stage.addActor(table);
   }

   @Override
   public void startProcessing()
   {
       table.reset();
       table.columnDefaults(0).pad(4);
   }

    @Override
    public void endProcessing()
    {
        stage.act();
        stage.draw();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DebugLabelComponent debug = dm.get(entity);
        TransformComponent transform = tm.get(entity);
        VelocityComponent velocity = mm.get(entity);
        HullComponent hull = hm.get(entity);
        ShieldComponent shield = sm.get(entity);
        GeneratorComponent generator = gm.get(entity);

        String textPos = "pos(" + Stringf.format("%.1f",transform.position.x) + "," + Stringf.format("%.1f",transform.position.y) + ")";
        String textVel = "vel(" + Stringf.format("%.1f",velocity.x) + "," + Stringf.format("%.1f",velocity.y) + ")";
        String textHull = "hull(" + Stringf.format("%.1f",hull.hullCurrent) + "," + Stringf.format("%.1f",hull.hullMax) + ")";
        String textShield = "shield(" + Stringf.format("%.1f",shield.shieldCurrent) + "," + Stringf.format("%.1f",shield.shieldMax) + ")";
        String textGenerator = "gen(" + Stringf.format("%.1f",generator.energyCurrent) + "," + Stringf.format("%.1f",generator.energyMax) + ")";

        table.add("Player", "small", Color.BLACK).getActor();
        table.row();
        table.add(textPos, "small", Color.BLACK).getActor();
        table.row();
        table.add(textVel, "small", Color.BLACK).getActor();
        table.row();
        table.add(textHull, "small", Color.BLACK).getActor();
        table.row();
        table.add(textShield, "small", Color.BLACK).getActor();
        table.row();
        table.add(textGenerator, "small", Color.BLACK).getActor();
        table.row();
    }

    private Drawable backgroundDrawable()
    {
        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.BLACK);
        bgPixmap.fill();
        return new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
    }

}
