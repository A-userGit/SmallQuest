package com.quest.services.logic.datasetters;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.subaction.subactdata.SimpleIntActionData;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.models.LocalStat;

import java.util.Map;
import java.util.stream.Stream;

public class StatSimpleSetter implements DataSetter<LocalStat, SimpleIntActionData> {
    @Override
    public void setData(Map<Integer,LocalStat> dataItem, SimpleIntActionData actionData, Map<ActionItemFieldConnector, ReadableEnum> connectors) {
        Stream<ActionItemFieldConnector> statConnectors = connectors.keySet().stream()
                .filter(c->c.getType() == ItemType.STAT);

        for (:
             ) {
            
        }
    }
}
