package com.quest.commons.types.settertypes.datafiields;

import com.quest.commons.interfaces.ReadableEnum;

public enum ItemSimpleValue implements ReadableEnum {
    VALUE;

    @Override
    public ReadableEnum getValue(int ordinal) {
        return values()[ordinal];
    }
}
