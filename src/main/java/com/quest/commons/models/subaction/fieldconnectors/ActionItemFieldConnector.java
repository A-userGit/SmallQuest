package com.quest.commons.models.subaction.fieldconnectors;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.types.ActionConnectorType;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;

public class ActionItemFieldConnector {

    private ReadableEnum field;

    private ActionConnectorType connectorType;

    private ItemPlace place;

    private ItemType type;

    public ReadableEnum getField() {
        return field;
    }

    public void setField(ReadableEnum field) {
        this.field = field;
    }

    public ItemPlace getPlace() {
        return place;
    }

    public void setPlace(ItemPlace place) {
        this.place = place;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ActionConnectorType getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(ActionConnectorType connectorType) {
        this.connectorType = connectorType;
    }

    public ActionItemFieldConnector(ActionConnectorType connectorType) {
        this.connectorType = connectorType;
    }
}
