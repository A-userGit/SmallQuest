package com.quest.services.interfaces;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.services.models.RequirementModel;

public interface RequirementComparator {
    public int compare(ItemSupported item, RequirementModel requirement);
}
