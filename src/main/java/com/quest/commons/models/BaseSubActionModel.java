package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;
import com.quest.commons.models.subactdata.ActionDataTypes;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;

import java.util.function.Function;

public class BaseSubActionModel<T,R extends IdSupported> implements IdSupported {

    private int id;

    private ItemActionType itemActionType;

    private ActionDataTypes actionDataType;

    private

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

    public ItemActionType getItemActionType() {
        return itemActionType;
    }

    public void setItemActionType(ItemActionType itemActionType) {
        this.itemActionType = itemActionType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
