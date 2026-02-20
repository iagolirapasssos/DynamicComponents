package com.iagolirapassos.helpers;

import com.google.appinventor.components.common.OptionList;
import java.util.HashMap;
import java.util.Map;

public enum StylePreset implements OptionList<Integer> {
    Material(0),
    Neon(1),
    Elegant(2),
    Fun(3),
    Minimal(4),
    Glossy(5);

    private final int value;

    StylePreset(int value) {
        this.value = value;
    }

    public Integer toUnderlyingValue() {
        return value;
    }

    private static final Map<Integer, StylePreset> lookup = new HashMap<>();

    static {
        for (StylePreset preset : StylePreset.values()) {
            lookup.put(preset.toUnderlyingValue(), preset);
        }
    }

    public static StylePreset fromUnderlyingValue(Integer value) {
        return lookup.get(value);
    }
}