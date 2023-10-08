package com.quest.services.logic.datasetters;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.FieldValueItemPlace;
import com.quest.commons.models.subactdata.SimpleIntActionData;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.models.LocalStat;

import java.util.Map;
import java.util.stream.Stream;

public class StatSimpleSetter implements DataSetter<LocalStat, SimpleIntActionData> {
    @Override
    public void setData(Map<Integer,LocalStat> dataItem, SimpleIntActionData actionData, Map<FieldValueItemPlace, ReadableEnum> connectors) {
        Stream<FieldValueItemPlace> statConnectors = connectors.keySet().stream()
                .filter(c -> c.getPlace() == ItemPlace.STAT && c.getType() == ItemType.STAT);
        statConnectors.sorted((o1, o2) -> )
        for (:
             ) {
            
        }
    }
}
