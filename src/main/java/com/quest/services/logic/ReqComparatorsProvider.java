package com.quest.services.logic;

import com.quest.commons.types.ItemType;
import com.quest.commons.types.RequirementType;
import com.quest.services.comparators.ItemQuantityComparator;
import com.quest.services.comparators.StatComparator;
import com.quest.services.interfaces.RequirementComparator;

public class ReqComparatorsProvider {

    public static RequirementComparator getComparator(ItemType type, RequirementType requirementType)
    {
        switch (type)
        {
            case STAT: return new StatComparator();
            case SIMPLE: new ItemQuantityComparator();
        }
        return null;
    }
}
