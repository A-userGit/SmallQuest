  package com.quest.dao.models;

public class RequirementEntity extends ItemEntity{

    private boolean consumed;

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        consumed = consumed;
    }

    public RequirementEntity(int id, String description, int value, boolean consumed) {
        super(id, description, value);
        this.consumed = consumed;
    }
}
