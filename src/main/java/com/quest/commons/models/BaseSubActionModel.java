package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;
import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.subactdata.ActionDataInterface;
import com.quest.commons.types.ActionDataTypes;
import com.quest.commons.types.ActionFunctionType;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemPlace;

import java.util.HashMap;
import java.util.Map;

public class BaseSubActionModel implements IdSupported {

    private int id;

    private ItemActionType itemActionType;

    private ActionDataTypes actionDataType;

    private ActionFunctionType actionFunctionType;

    private ItemPlace itemPlace;

    private ActionDataInterface changeData;

    private Map<FieldValueItemPlace,ReadableEnum> sourceConsumerPairs;

    public BaseSubActionModel(int id) {
        this.id = id;
        sourceConsumerPairs = new HashMap<>();

    }

    public void setChangeData(ActionDataInterface changeData) {
        this.changeData = changeData;
    }

    public Map<FieldValueItemPlace, ReadableEnum> getSourceConsumerPairs() {
        return sourceConsumerPairs;
    }

    public void setSourceConsumerPairs(Map<FieldValueItemPlace, ReadableEnum> sourceConsumerPairs) {
        this.sourceConsumerPairs = sourceConsumerPairs;
    }

    public ActionDataTypes getActionDataType() {
        return actionDataType;
    }

    public void setActionDataType(ActionDataTypes actionDataType) {
        this.actionDataType = actionDataType;
    }

    public ActionFunctionType getActionFunctionType() {
        return actionFunctionType;
    }

    public void setActionFunctionType(ActionFunctionType actionFunctionType) {
        this.actionFunctionType = actionFunctionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemActionType getItemActionType() {
        return itemActionType;
    }

    public void setItemActionType(ItemActionType itemActionType) {
        this.itemActionType = itemActionType;
    }

    public ItemPlace getItemType() {
        return itemPlace;
    }

    public void setItemType(ItemPlace itemPlace) {
        this.itemPlace = itemPlace;
    }

    public ActionDataInterface getChangeData() {
        return changeData;
    }

}
