package com.quest.services.models;

import com.quest.commons.interfaces.ItemIdSupported;
import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.ItemModel;
import com.quest.services.interfaces.Executable;

import java.util.HashMap;

public class SubActionModel extends BaseSubActionModel implements Executable {
    public SubActionModel(int id, boolean random, int startAmount, int randomTopBorder) {
        super(id, random, startAmount, randomTopBorder);
    }

    private ItemModel item;

    private ItemModel toGenerateItem;

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public ItemModel getToGenerateItem() {
        return toGenerateItem;
    }

    public void setToGenerateItem(ItemModel toGenerateItem) {
        this.toGenerateItem = toGenerateItem;
    }
}
