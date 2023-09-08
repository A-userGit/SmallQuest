package com.quest.commons.models;

import com.quest.commons.models.BaseModel;

public class ItemModel extends BaseModel {

    private boolean visibleIfZero;

    private boolean infinite;


    public ItemModel(int id, String description, boolean visibleIfZero, boolean infinite) {
        super(id, description);
        this.visibleIfZero = visibleIfZero;
        this.infinite = infinite;
    }

    public boolean isVisibleIfZero() {
        return visibleIfZero;
    }

    public void setVisibleIfZero(boolean visibleIfZero) {
        this.visibleIfZero = visibleIfZero;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }
}
