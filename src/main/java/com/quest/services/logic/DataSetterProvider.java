package com.quest.services.logic;

import com.quest.commons.types.ActionDataTypes;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.logic.datasetters.StatSimpleSetter;

public class DataSetterProvider {
    public static DataSetter getDataSetter(ItemType itemType, ActionDataTypes actionDataType)
    {
        switch (actionDataType)
        {
            case RANDOM: return getRandomSetterByItem(itemType);
            case INT: return getIntSetterByItem(itemType);
        }
        return null;
    }

    protected static DataSetter getIntSetterByItem(ItemType itemType) {
        switch (itemType)
        {
            case STAT: ;
        }
        return null;
    }

    protected static DataSetter getRandomSetterByItem(ItemType itemType)
    {
        switch (itemType)
        {
            case STAT: return new StatSimpleSetter();
            case SIMPLE:;
        }
        return null;
    }
}
