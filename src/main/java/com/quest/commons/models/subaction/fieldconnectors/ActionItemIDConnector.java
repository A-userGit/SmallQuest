package com.quest.commons.models.subaction.fieldconnectors;

import com.quest.commons.types.ActionConnectorType;

public class ActionItemIDConnector extends ActionItemFieldConnector{
    private int id;

    public ActionItemIDConnector(ActionConnectorType connectorType) {
        super(connectorType);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
