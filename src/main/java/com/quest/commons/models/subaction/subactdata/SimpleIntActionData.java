package com.quest.commons.models.subaction.subactdata;

import com.quest.services.logic.functions.argtypes.SimpleArithmetics;

public class SimpleIntActionData implements ActionDataInterface{
    private int changeValue;

    private SimpleArithmetics action;

    public SimpleIntActionData(int changeValue) {
        this.changeValue = changeValue;
    }

    public int getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(int changeValue) {
        this.changeValue = changeValue;
    }

    public SimpleArithmetics getAction() {
        return action;
    }

    public void setAction(SimpleArithmetics action) {
        this.action = action;
    }
}
