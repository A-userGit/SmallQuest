package com.quest.services.models;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.ItemModel;

public class LocalItem implements ItemSupported {
    private int value;

    private ItemModel item;

    public LocalItem(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }
}
