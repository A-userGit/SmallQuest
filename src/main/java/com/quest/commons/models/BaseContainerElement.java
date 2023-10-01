package com.quest.commons.models;

import com.quest.commons.types.ItemPlace;

public class BaseContainerElement {
    private ItemPlace type;
    private int quantity;

    public ItemPlace getItemPlace() {
        return type;
    }

    public void setItemPlace(ItemPlace type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
