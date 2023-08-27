package com.quest.services.models;

import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.ItemModel;

public class SubActionModel extends BaseSubActionModel {
    public SubActionModel(int id, boolean random, int startAmount, int randomTopBorder) {
        super(id, random, startAmount, randomTopBorder);
    }

    private ItemModel item;

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }
}
