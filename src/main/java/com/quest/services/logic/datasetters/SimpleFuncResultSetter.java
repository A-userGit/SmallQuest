package com.quest.services.logic.datasetters;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemIDConnector;
import com.quest.commons.models.subaction.subactdata.SimpleIntActionData;
import com.quest.commons.types.ActionConnectorType;
import com.quest.commons.types.ActionDataTypes;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.logic.functions.NumFuncAnswer;
import com.quest.services.models.LocalStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleFuncResultSetter implements DataSetter<NumFuncAnswer, SimpleIntActionData> {

    @Override
    public SimpleIntActionData setData(Map<Integer, NumFuncAnswer> dataItem, SimpleIntActionData actionData, Map<ActionItemFieldConnector, ReadableEnum> connectors, List<ActionItemFieldConnector> keys) {
        for (ActionItemFieldConnector connector : keys) {
            ActionItemIDConnector idConnector = (ActionItemIDConnector) connector;
            NumFuncAnswer numFuncAnswer = dataItem.get(idConnector.getId());
            if(numFuncAnswer == null)
                continue;
            actionData.setChangeValue((int) numFuncAnswer.getValue());
        }
        return actionData;
    }

    @Override
    public String getCode() {
        ArrayList<Integer> code = new ArrayList<>();
        code.add(ItemType.FUNC_RES.ordinal());
        code.add(ActionConnectorType.ID_DEPENDENT.ordinal());
        code.add(ActionDataTypes.INT.ordinal());
        return code.toString();
    }
}
