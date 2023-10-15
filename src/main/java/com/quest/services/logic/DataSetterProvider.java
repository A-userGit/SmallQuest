package com.quest.services.logic;

import com.quest.commons.types.ActionConnectorType;
import com.quest.commons.types.ActionDataTypes;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.logic.datasetters.ItemSimpleSetterById;
import com.quest.services.logic.datasetters.SimpleFuncResultSetter;
import com.quest.services.logic.datasetters.StatSimpleSetterById;

import java.util.ArrayList;
import java.util.HashMap;


public class DataSetterProvider {

    private HashMap<String, DataSetter> setters = new HashMap<>();
    public DataSetterProvider()
    {
        ItemSimpleSetterById itemSimpleSetterById = new ItemSimpleSetterById();
        setters.put(itemSimpleSetterById.getCode(),itemSimpleSetterById);
        StatSimpleSetterById statSimpleSetterById = new StatSimpleSetterById();
        setters.put(statSimpleSetterById.getCode(),statSimpleSetterById);
        SimpleFuncResultSetter resultSetter = new SimpleFuncResultSetter();
        setters.put(resultSetter.getCode(), resultSetter);
    }
    public DataSetter getDataSetter(ItemType itemType, ActionDataTypes actionDataType, ActionConnectorType connectorType)
    {
        ArrayList<Integer> setterCode = new ArrayList<>();
        setterCode.add(itemType.ordinal());
        setterCode.add(connectorType.ordinal());
        setterCode.add(actionDataType.ordinal());
        return setters.get(setterCode.toString());
    }
}
