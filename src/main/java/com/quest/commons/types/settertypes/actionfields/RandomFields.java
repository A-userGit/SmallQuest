package com.quest.commons.types.settertypes.actionfields;

import com.quest.commons.interfaces.ReadableEnum;

public enum RandomFields implements ReadableEnum {
    TOP,BOTTOM;

    @Override
    public ReadableEnum getValue(int ordinal) {
        return values()[ordinal];
    }
}
