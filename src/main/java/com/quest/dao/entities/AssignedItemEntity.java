package com.quest.dao.entities;

import com.quest.commons.interfaces.ItemIdSupported;

public class AssignedItemEntity implements ItemIdSupported {
    private int id;

    private int itemId;

    private int amount;

    public AssignedItemEntity(int id, int itemId, int amount) {
        this.id = id;
        this.itemId = itemId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
