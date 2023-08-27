package com.quest.dao.entities;

import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;

public class SubActionEntity extends BaseSubActionModel {

    private int itemId;


    public SubActionEntity(int id, boolean random, int itemId, int startAmount, int randomTopBorder) {
        super(id, random, startAmount, randomTopBorder);
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
