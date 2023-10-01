package com.quest.dao.entities;

import com.quest.commons.models.BaseSubActionModel;

public class SubActionEntity extends BaseSubActionModel {

    private int itemId;

    public SubActionEntity(int id, int itemId) {
        super(id);
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
