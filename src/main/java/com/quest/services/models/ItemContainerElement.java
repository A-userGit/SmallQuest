package com.quest.services.models;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.BaseContainerElement;
import com.quest.commons.models.ItemModel;

public class ItemContainerElement extends BaseContainerElement implements ItemSupported {
    private ItemModel innerItem;

    public ItemModel getItem() {
        return innerItem;
    }

    public void setItem(ItemModel innerItem) {
        this.innerItem = innerItem;
    }
}
