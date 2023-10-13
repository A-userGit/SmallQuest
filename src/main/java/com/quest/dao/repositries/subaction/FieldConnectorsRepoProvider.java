package com.quest.dao.repositries.subaction;

import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.types.ActionConnectorType;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;
import com.quest.dao.repositries.RepositoryUtility;
import com.quest.dao.repositries.subaction.connectorproviders.ContainerConnectorProvider;
import com.quest.dao.repositries.subaction.connectorproviders.IDConnectorProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FieldConnectorsRepoProvider {

    public static ActionItemFieldConnector getConnector(ObjectInputStream inputStream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ActionConnectorType connectorType = ActionConnectorType.values()[inputStream.readInt()];
        ActionItemFieldConnector actionItemFieldConnector = getFullConnector(connectorType, inputStream);
        actionItemFieldConnector.setPlace(ItemPlace.values()[inputStream.readInt()]);
        actionItemFieldConnector.setField(RepositoryUtility.readEnum(inputStream));
        actionItemFieldConnector.setType(ItemType.values()[inputStream.readInt()]);
        return actionItemFieldConnector;
    }
    protected static ActionItemFieldConnector getFullConnector(ActionConnectorType connectorType, ObjectInputStream inputStream) throws IOException {
        switch (connectorType)
        {
            case PLACE_DEPENDENT: return new ActionItemFieldConnector(connectorType);
            case ID_DEPENDENT: return IDConnectorProvider.getConnector(inputStream);
            case CONTAINER_DOUBLE_ID: return ContainerConnectorProvider.getConnector(inputStream);
        }
        return null;
    }

    public static void writeConnector(ObjectOutputStream outputStream, ActionItemFieldConnector connector) throws IOException {
        outputStream.writeInt(connector.getConnectorType().ordinal());
        writeFullConnector(connector, outputStream);
        outputStream.writeInt(connector.getPlace().ordinal());
        RepositoryUtility.writeEnum(outputStream, connector.getField());
        outputStream.writeInt(connector.getType().ordinal());
    }

    protected static void writeFullConnector(ActionItemFieldConnector connector, ObjectOutputStream outputStream) throws IOException {
        switch (connector.getConnectorType())
        {
            case PLACE_DEPENDENT: return;
            case ID_DEPENDENT:  IDConnectorProvider.writeConnector(outputStream, connector);
            break;
            case CONTAINER_DOUBLE_ID: ContainerConnectorProvider.writeConnector(outputStream, connector);
            break;
        }
    }
}
