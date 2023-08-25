package com.quest.commons.models;

import com.quest.commons.models.BaseModel;

public class ItemModel extends BaseModel {
    private int value;

    private boolean visibleIfZero;

    public ItemModel(int id, String description, int value) {
        super(id, description);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isVisibleIfZero() {
        return visibleIfZero;
    }

    public void setVisibleIfZero(boolean visibleIfZero) {
        this.visibleIfZero = visibleIfZero;
    }
}
