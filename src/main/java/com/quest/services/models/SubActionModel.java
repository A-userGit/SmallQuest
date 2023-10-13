package com.quest.services.models;

import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.ItemModel;
import com.quest.services.interfaces.ActionFunction;
import com.quest.services.interfaces.Executable;

public class SubActionModel extends BaseSubActionModel {
    public SubActionModel(int id) {
        super(id);
    }

    private ItemModel item;

    private ActionFunction changeFunction;

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public ActionFunction getChangeFunction() {
        return changeFunction;
    }

    public void setChangeFunction(ActionFunction changeFunction) {
        this.changeFunction = changeFunction;
    }
}
