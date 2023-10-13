package com.quest.commons.models;

import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;
import com.quest.commons.types.RequirementType;
import com.quest.commons.types.RestrictionType;

public class BaseRequirementModel extends BaseModel{

    private boolean blocker;

    private  int value;

    private ItemPlace itemPlace;

    private RestrictionType restrictionType;

    private RequirementType requirementType;

    private ItemType itemType;

    public BaseRequirementModel(int id, String description, int value, boolean blocker) {
        super(id, description);
        this.blocker = blocker;
        this.value = value;
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

    public ItemPlace getItemPlace() {
        return itemPlace;
    }

    public void setItemPlace(ItemPlace itemPlace) {
        this.itemPlace = itemPlace;
    }

    public RestrictionType getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(RestrictionType restrictionType) {
        this.restrictionType = restrictionType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public RequirementType getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(RequirementType requirementType) {
        this.requirementType = requirementType;
    }
}
