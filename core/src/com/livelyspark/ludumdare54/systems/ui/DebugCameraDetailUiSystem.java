package com.livelyspark.ludumdare54.systems.ui;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.livelyspark.ludumdare54.ashley.IteratingSystemBetter;
import com.livelyspark.ludumdare54.components.DebugLabelComponent;
import com.livelyspark.ludumdare54.components.TransformComponent;
import com.livelyspark.ludumdare54.components.physics.VelocityComponent;
import text.formic.Stringf;


public class DebugCameraDetailUiSystem extends EntitySystem {

    private final Camera camera;
    private Stage stage;
    private Table table;

    public DebugCameraDetailUiSystem(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);

        stage = new Stage();
        Skin uiSkin = new Skin(Gdx.files.internal("data/ui/plain.json"));
        Drawable tableBackground = uiSkin.getDrawable("textfield");


        table = new Table(uiSkin);

        //table.setDebug(true);
        table.setWidth(200);
        table.setHeight(100);
        table.setX(200);
        table.setY(stage.getHeight() - table.getHeight());
        table.background(tableBackground);

        table.columnDefaults(0).center();

        stage.addActor(table);
   }

    @Override
    public void update (float deltaTime) {
        table.reset();
        table.columnDefaults(0).pad(4);

        String postext = "pos(" + Stringf.format("%.2f",camera.position.x) + "," + Stringf.format("%.2f",camera.position.y) + ") ";
        String  viewporttext = "viewport(" + Stringf.format("%.2f",camera.viewportWidth) + "," + Stringf.format("%.2f",camera.viewportHeight) + ") ";

        table.add("Camera", "small", Color.BLACK).getActor();
        table.row();
        table.add(postext, "small", Color.BLACK).getActor();
        table.row();
        table.add(viewporttext, "small", Color.BLACK).getActor();
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
