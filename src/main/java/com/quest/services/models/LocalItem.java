package com.quest.services.models;

import com.quest.commons.models.ItemModel;

public class LocalItem extends ItemModel {
    private int value;

    public LocalItem(int id, String description, boolean visibleIfZero, boolean infinite, int value) {
        super(id, description, visibleIfZero, infinite);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
