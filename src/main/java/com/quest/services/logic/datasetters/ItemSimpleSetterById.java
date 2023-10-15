package com.quest.services.logic.datasetters;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemIDConnector;
import com.quest.commons.models.subaction.subactdata.SimpleIntActionData;
import com.quest.commons.types.ActionConnectorType;
import com.quest.commons.types.ActionDataTypes;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.models.LocalItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemSimpleSetterById implements DataSetter<LocalItem, SimpleIntActionData> {
    @Override
    public SimpleIntActionData setData(Map<Integer, LocalItem> dataItem, SimpleIntActionData actionData, Map<ActionItemFieldConnector, ReadableEnum> connectors, List<ActionItemFieldConnector> keys) {
        for (ActionItemFieldConnector connector : keys) {
            ActionItemIDConnector idConnector = (ActionItemIDConnector) connector;
            LocalItem localItem = dataItem.get(idConnector.getId());
            if(localItem == null)
                continue;
            actionData.setChangeValue(localItem.getValue());
        }
        return actionData;
    }

    @Override
    public String getCode() {
        ArrayList<Integer> code = new ArrayList<>();
        code.add(ItemType.SIMPLE.ordinal());
        code.add(ActionConnectorType.ID_DEPENDENT.ordinal());
        code.add(ActionDataTypes.INT.ordinal());
        return code.toString();
    }
}
