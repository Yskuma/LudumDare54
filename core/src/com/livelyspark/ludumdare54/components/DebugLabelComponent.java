package com.livelyspark.ludumdare54.components;

import com.badlogic.ashley.core.Component;

public class DebugLabelComponent implements Component {
    public String label;

    public DebugLabelComponent(String label)
    {
        this.label = label;
    }
}
