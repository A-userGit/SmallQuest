package com.quest.dao.entities;

import com.quest.commons.interfaces.ItemIdSupported;
import com.quest.commons.models.BaseContainerElement;

public class ContainerIdElement extends BaseContainerElement implements ItemIdSupported {
    private int itemId;

    public ContainerIdElement(int id)
    {
        this.itemId = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
