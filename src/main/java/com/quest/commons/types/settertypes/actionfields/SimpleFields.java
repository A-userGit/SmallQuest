package com.quest.commons.types.settertypes.actionfields;

import com.quest.commons.interfaces.ReadableEnum;

public enum SimpleFields implements ReadableEnum {
    VALUE_INT;

    @Override
    public ReadableEnum getValue(int ordinal) {
        return values()[ordinal];
    }
}
