package com.quest.dao.entities;

public class AssignedStatEntity extends AssignedItemEntity{

    private boolean critical;

    public AssignedStatEntity(int id, int itemId, int amount, boolean critical) {
        super(id, itemId, amount);
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
}
