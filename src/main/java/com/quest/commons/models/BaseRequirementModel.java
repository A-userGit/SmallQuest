package com.quest.commons.models;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;
import com.quest.commons.types.RestrictionType;

public class BaseRequirementModel extends BaseModel{

    private ItemActionType actionType;

    private boolean blocker;

    private  int value;

    private ItemType itemType;

    private RestrictionType restrictionType;

    public BaseRequirementModel(int id, String description, int value, boolean blocker) {
        super(id, description);
        this.blocker = blocker;
        this.value = value;
    }

    public ItemActionType getActionType() {
        return actionType;
    }

    public void setActionType(ItemActionType actionType) {
        this.actionType = actionType;
    }

    public boolean isBlocker() {
        return blocker;
    }

    public void setBlocker(boolean blocker) {
        this.blocker = blocker;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public RestrictionType getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(RestrictionType restrictionType) {
        this.restrictionType = restrictionType;
    }
}
