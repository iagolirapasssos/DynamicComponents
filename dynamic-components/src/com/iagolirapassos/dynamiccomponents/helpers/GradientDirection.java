package com.iagolirapassos.helpers;

import com.google.appinventor.components.common.OptionList;
import java.util.HashMap;
import java.util.Map;

public enum GradientDirection implements OptionList<Integer> {
    TopBottom(0),
    BottomTop(1),
    LeftRight(2),
    RightLeft(3),
    TlBr(4),
    TrBl(5);

    private final int value;

    GradientDirection(int value) {
        this.value = value;
    }

    public Integer toUnderlyingValue() {
        return value;
    }

    private static final Map<Integer, GradientDirection> lookup = new HashMap<>();

    static {
        for (GradientDirection direction : GradientDirection.values()) {
            lookup.put(direction.toUnderlyingValue(), direction);
        }
    }

    public static GradientDirection fromUnderlyingValue(Integer value) {
        return lookup.get(value);
    }
}