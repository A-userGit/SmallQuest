package com.quest.services.comparators;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.services.interfaces.RequirementComparator;
import com.quest.services.models.LocalItem;
import com.quest.services.models.RequirementModel;

public class ItemQuantityComparator implements RequirementComparator {
    @Override
    public int compare(ItemSupported item, RequirementModel requirement) {
        LocalItem localItem = (LocalItem) item;
        if(localItem.getValue() == requirement.getValue())
            return 0;
        if(localItem.getValue() < requirement.getValue())
            return -1;
        return 1;
    }
}
