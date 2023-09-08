package com.quest.services.comparators;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.services.interfaces.RequirementComparator;
import com.quest.services.models.LocalStat;
import com.quest.services.models.RequirementModel;

public class StatComparator implements RequirementComparator {
    @Override
    public int compare(ItemSupported item, RequirementModel requirement) {
        LocalStat stat = (LocalStat) item;
        if(stat.getAmount() == requirement.getValue())
            return 0;
        if(stat.getAmount() < requirement.getValue())
            return -1;
        return 1;
    }
}
