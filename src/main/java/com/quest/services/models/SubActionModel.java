package com.quest.services.models;

import com.quest.commons.interfaces.ItemIdSupported;
import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.ItemModel;
import com.quest.services.interfaces.Executable;

import java.util.HashMap;
import java.util.function.Function;

public class SubActionModel<T,R> extends BaseSubActionModel implements Executable {
    public SubActionModel(int id, Function<T,R> function) {
        super(id, function);
    }

    private ItemModel item;


    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }
}
