package com.quest.services.interfaces;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.subaction.subactdata.ActionDataInterface;

import java.util.Map;

public interface DataSetter<I extends ItemSupported, A extends ActionDataInterface> {
    public void setData(Map<Integer,I> dataItem, A actionData, Map<ActionItemFieldConnector, ReadableEnum> connectors);
}
