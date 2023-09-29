package com.quest.dao.entities;

import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.SubActionMidResult;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;

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
