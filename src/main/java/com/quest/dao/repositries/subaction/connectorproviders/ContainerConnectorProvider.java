package com.quest.dao.repositries.subaction.connectorproviders;

import com.quest.commons.models.subaction.fieldconnectors.ActionItemContainerConnector;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.types.ActionConnectorType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContainerConnectorProvider {
    public static ActionItemContainerConnector getConnector(ObjectInputStream inputStream) throws IOException {
        ActionItemContainerConnector connector = new ActionItemContainerConnector(ActionConnectorType.CONTAINER_DOUBLE_ID);
        connector.setId(inputStream.readInt());
        connector.setContainerId(inputStream.readInt());
        return connector;
    }

    public static void writeConnector(ObjectOutputStream outputStream, ActionItemFieldConnector connector) throws IOException {
        ActionItemContainerConnector containerConnector = (ActionItemContainerConnector) connector;
        outputStream.writeInt(containerConnector.getId());
        outputStream.writeInt(containerConnector.getContainerId());
    }
}
