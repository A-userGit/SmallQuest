package com.quest.services.models;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.BaseRequirementModel;
import com.quest.commons.models.ItemModel;
import com.quest.services.interfaces.IValidated;
import com.quest.services.interfaces.RequirementComparator;

import java.util.Comparator;
import java.util.HashMap;

public class RequirementModel extends BaseRequirementModel implements IValidated {

    private ItemModel item;

    public RequirementModel(int id, String description, int value, boolean blocker) {
        super(id, description, value, blocker);
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    @Override
    public boolean isValid(HashMap itemsToCheck, RequirementComparator comparator) {
        ItemSupported item = (ItemSupported) itemsToCheck.get(this.getItem().getId());
        if(item == null)
            return false;
        int answer = comparator.compare(item,this);
        switch (this.getRestrictionType())
        {
            case EQ -> {
                if(answer == 0)
                    return true;
            }
            case LEQ -> {
                if(answer == -1)
                    return true;
            }
            case GEQ -> {
                if (answer == 1)
                    return true;
            }
            case NEQ -> {
                if (answer != 0)
                    return true;
            }
        }
        return false;
    }
}