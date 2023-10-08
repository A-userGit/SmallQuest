package com.quest.services.interfaces;

import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.FieldValueItemPlace;
import com.quest.commons.models.subactdata.ActionDataInterface;

import java.util.Map;

public interface DataSetter<I extends ItemSupported, A extends ActionDataInterface> {
    public void setData(Map<Integer,I> dataItem, A actionData, Map<FieldValueItemPlace, ReadableEnum> connectors);
}
