package com.quest.services.models;

import com.quest.commons.interfaces.IdSupported;
import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.BaseLocalStatModel;
import com.quest.commons.models.ItemModel;

public class LocalStat extends BaseLocalStatModel implements ItemSupported {

    public LocalStat(int id, int amount, boolean critical) {
        super(id, amount, critical);
    }

    private ItemModel item;

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel itemModel) {
        this.item = itemModel;
    }
}
