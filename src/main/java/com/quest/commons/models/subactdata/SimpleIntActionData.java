package com.quest.commons.models.subactdata;

public class SimpleIntActionData implements ActionDataInterface{
    private int changeValue;

    public SimpleIntActionData(int changeValue) {
        this.changeValue = changeValue;
    }

    public int getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(int changeValue) {
        this.changeValue = changeValue;
    }
}
