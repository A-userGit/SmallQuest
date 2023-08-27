package com.quest.services.models;

import com.quest.commons.models.BaseRequirementModel;
import com.quest.commons.models.ItemModel;

public class RequirementModel extends BaseRequirementModel {

    private ItemModel item;

    public RequirementModel(int id, String description, int value, boolean blocker) {
        super(id, description, value, blocker);
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }
}