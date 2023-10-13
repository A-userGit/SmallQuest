package com.quest.dao.repositries.subaction.connectorproviders;

import com.quest.commons.models.subaction.fieldconnectors.ActionItemContainerConnector;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemIDConnector;
import com.quest.commons.types.ActionConnectorType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IDConnectorProvider {
    public static ActionItemIDConnector getConnector(ObjectInputStream inputStream) throws IOException {
        ActionItemIDConnector connector = new ActionItemContainerConnector(ActionConnectorType.ID_DEPENDENT);
        connector.setId(inputStream.readInt());
        return connector;
    }

    public static void writeConnector(ObjectOutputStream outputStream, ActionItemFieldConnector connector) throws IOException {
        ActionItemIDConnector idConnector = (ActionItemIDConnector) connector;
        outputStream.writeInt(idConnector.getId());
    }
}
