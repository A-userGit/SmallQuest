package com.quest.commons.models;

public class BaseNodeModel extends BaseModel{

    private boolean isFinale;

    public BaseNodeModel(int id, String description) {
        super(id, description);
    }

    public boolean isFinale() {
        return isFinale;
    }

    public void setFinale(boolean finale) {
        isFinale = finale;
    }
}
