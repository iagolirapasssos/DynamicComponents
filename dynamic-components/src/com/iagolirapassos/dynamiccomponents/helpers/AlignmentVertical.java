package com.iagolirapassos.helpers;

import com.google.appinventor.components.common.OptionList;

public enum AlignmentVertical implements OptionList<Integer> {
	Top(0),
    Center(1),
    Bottom(2);

    private int value;

    AlignmentVertical(int value) {
        this.value = value;
    }

    @Override
    public Integer toUnderlyingValue() {
        return value;
    }
}