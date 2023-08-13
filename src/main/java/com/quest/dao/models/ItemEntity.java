package com.quest.dao.models;

public class ItemEntity extends  BaseEntity{
    private int value;

    public ItemEntity(int id, String description, int value) {
        super(id, description);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
