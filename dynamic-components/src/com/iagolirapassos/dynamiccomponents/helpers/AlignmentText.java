package com.iagolirapassos.helpers;

import com.google.appinventor.components.common.OptionList;

public enum AlignmentText implements OptionList<Integer> {
    Left(0),
    Center(1),
    Right(2);

    private int value;

    AlignmentText(int value) {
        this.value = value;
    }

    @Override
    public Integer toUnderlyingValue() {
        return value;
    }
}