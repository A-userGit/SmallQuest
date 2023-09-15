package com.quest.commons.models;

import com.quest.commons.types.ItemType;

public class BaseContainerElement {
    private ItemType type;
    private int quantity;

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
