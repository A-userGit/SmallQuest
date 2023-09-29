package com.quest.services.models;

import com.quest.commons.interfaces.ItemIdSupported;
import com.quest.commons.models.BaseSubActionModel;
import com.quest.commons.models.ItemModel;
import com.quest.commons.models.subactdata.ActionDataInterface;
import com.quest.services.interfaces.Executable;

import java.util.HashMap;
import java.util.function.Function;

public class SubActionModel<R> extends BaseSubActionModel implements Executable {
    public SubActionModel(int id) {
        super(id);
    }

    private ItemModel item;

    private Function<ActionDataInterface,R> changeFunction;

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    public Function<ActionDataInterface, R> getChangeFunction() {
        return changeFunction;
    }

    public void setChangeFunction(Function<ActionDataInterface, R> changeFunction) {
        this.changeFunction = changeFunction;
    }
}
