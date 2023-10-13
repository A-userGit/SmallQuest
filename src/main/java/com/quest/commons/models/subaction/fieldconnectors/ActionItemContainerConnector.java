package com.quest.commons.models.subaction.fieldconnectors;

import com.quest.commons.types.ActionConnectorType;

public class ActionItemContainerConnector extends ActionItemIDConnector{
    private int containerId;

    public ActionItemContainerConnector(ActionConnectorType connectorType) {
        super(connectorType);
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }
}
