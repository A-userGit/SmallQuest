package com.quest.services.models;

import com.quest.commons.models.BaseSubActionModel;

public class SubActionModel extends BaseSubActionModel {
    public SubActionModel(int id, boolean random, int startAmount, int randomTopBorder) {
        super(id, random, startAmount, randomTopBorder);
    }

    private LocalItem item;

    public LocalItem getItem() {
        return item;
    }

    public void setItem(LocalItem item) {
        this.item = item;
    }
}
