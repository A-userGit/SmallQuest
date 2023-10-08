package com.quest.commons.models;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;

public class FieldValueItemPlace {
    private int id;

    private ReadableEnum field;

    private

    private ItemPlace place;

    private ItemType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReadableEnum getField() {
        return field;
    }

    public void setField(ReadableEnum field) {
        this.field = field;
    }

    public ItemPlace getPlace() {
        return place;
    }

    public void setPlace(ItemPlace place) {
        this.place = place;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
