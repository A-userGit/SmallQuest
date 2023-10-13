package com.quest.dao.repositries.subaction.subactdata;

import com.quest.commons.types.ActionDataTypes;
import com.quest.dao.repositries.subaction.ActionDataSourceInterface;

public class ActionDataProvider {
    public static ActionDataSourceInterface getProvider(ActionDataTypes actionDataType)
    {
        switch (actionDataType){
            case INT -> {
                return new SimpleValActionProvider();
            }
            case RANDOM -> {
                return new RandomActionProvider();
            }
        }
        return null;
    }
}
