package com.quest.services.interfaces;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;
import com.quest.commons.types.RequirementType;
import com.quest.services.models.RequirementModel;

import java.util.HashMap;
import java.util.List;

public interface IValidated <T extends ItemSupported>{

    public boolean isValid(HashMap<Integer, T> itemsToCheck,RequirementComparator comparator);

    public ItemType getItemType();

    public RequirementType getType();

    public ItemPlace getPlace();
}
