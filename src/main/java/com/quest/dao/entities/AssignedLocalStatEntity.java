package com.quest.dao.entities;

import com.quest.commons.interfaces.ItemIdSupported;
import com.quest.commons.models.BaseLocalStatModel;

public class AssignedLocalStatEntity extends BaseLocalStatModel implements ItemIdSupported {

    private int itemId;

    public AssignedLocalStatEntity(int id, int itemId, int amount, boolean critical) {
        super(id, amount, critical);
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
