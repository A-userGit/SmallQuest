  package com.quest.dao.models;

  import java.io.Serializable;

  public class RequirementEntity extends ItemEntity {

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

    public RequirementEntity()
    {
        super(0, "", 0);
        this.consumed = false;
    }
}
