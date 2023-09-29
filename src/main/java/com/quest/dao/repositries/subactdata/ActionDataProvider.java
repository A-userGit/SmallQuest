package com.quest.dao.repositries.subactdata;

import com.quest.commons.types.ActionDataTypes;

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
