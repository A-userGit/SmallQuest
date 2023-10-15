package com.quest.services.models;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.ItemModel;
import com.quest.services.interfaces.ActionFunction;

public class SubActionModel extends BaseSubActionModel {
    public SubActionModel(int id) {
        super(id);
    }

    private ItemSupported item;

    private int itemId;

    private ActionFunction changeFunction;

    public ItemSupported getItem() {
        return item;
    }

    public void setItem(ItemSupported item) {
        this.item = item;
    }

    public ActionFunction getChangeFunction() {
        return changeFunction;
    }

    public void setChangeFunction(ActionFunction changeFunction) {
        this.changeFunction = changeFunction;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
