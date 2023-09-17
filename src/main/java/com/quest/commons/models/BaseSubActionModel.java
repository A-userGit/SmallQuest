package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;
import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;

import java.util.function.Function;

public class BaseSubActionModel<T,R extends ItemSupported> implements IdSupported {

    private int id;

    private ItemActionType subActionType;

    private ItemType itemType;

    private T changeData;

    private Function<T,R> changeFunction;

    public BaseSubActionModel(int id,Function<T,R> changeFunction) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemActionType getSubActionType() {
        return subActionType;
    }

    public void setSubActionType(ItemActionType subActionType) {
        this.subActionType = subActionType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
