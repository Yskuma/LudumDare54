package com.livelyspark.ludumdare54.UI;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.livelyspark.ludumdare54.enums.ShipParts;

public class ShipyardUIShipDesigner {

    public Container Generate(Drawable background){

        Container container = new Container();
        container.background(background);
        return container;
    }
}
