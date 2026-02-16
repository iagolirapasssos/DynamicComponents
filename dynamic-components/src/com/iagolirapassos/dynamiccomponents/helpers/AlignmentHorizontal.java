package io.dynamiccomponents.helpers;

import com.google.appinventor.components.common.OptionList;

public enum AlignmentHorizontal implements OptionList<Integer> {
    Left(0),
    Center(1),
    Right(2);

    private int value;

    AlignmentHorizontal(int value) {
        this.value = value;
    }

    @Override
    public Integer toUnderlyingValue() {
        return value;
    }
}