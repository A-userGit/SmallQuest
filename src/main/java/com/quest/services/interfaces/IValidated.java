package com.quest.services.interfaces;

import java.util.HashMap;
import java.util.List;

public interface IValidated <T extends Comparable>{

    public boolean isValid(HashMap<Integer, T> itemsToCheck,RequirementComparator comparator);
}
