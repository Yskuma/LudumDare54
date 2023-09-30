package com.livelyspark.ludumdare54.components.sound;

import com.badlogic.ashley.core.Component;

public class SoundComponent implements Component {
    String soundKey;

    public SoundComponent(String soundKey)
    {
        this.soundKey = soundKey;
    }
}
