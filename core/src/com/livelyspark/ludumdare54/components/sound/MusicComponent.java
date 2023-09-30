package com.livelyspark.ludumdare54.components.sound;

import com.badlogic.ashley.core.Component;

public class MusicComponent implements Component {
    String musicKey;

    public MusicComponent(String musicKey)
    {
        this.musicKey = musicKey;
    }
}
