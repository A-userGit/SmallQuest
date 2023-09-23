package com.quest.services.executors;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.Executor;
import com.quest.services.models.LocalItem;
import com.quest.services.models.SubActionModel;

import java.util.HashMap;

public class ItemExecutor implements Executor<HashMap<Integer,LocalItem>, SubActionModel> {

    @Override
    public SubActionModel execute(HashMap<Integer,LocalItem> dataMap, SubActionModel command) {
        SubActionModel additionalAction = null;
        LocalItem data = dataMap.get(command.getItem().getId());
        if(data == null)
        {
            data = new LocalItem(0);
            data.setItem(command.getItem());
        }
        command.get
        ItemActionType subActionType = command.getItemActionType();
        switch (subActionType) {
            case SPEND:
                data.setValue(data.getValue() - changeValue);
                break;
            case GET_PLAYER:
                data.setValue(data.getValue() + changeValue);
                break;
            case GET_PLAYER_INF:
                data.setValue(data.getMaxAmount());
                break;
            case SPEND: {
                additionalAction = new SubActionModel(-1, false, data.getValue(), 0);
                additionalAction.setItemActionType(ItemActionType.GET_PLAYER);
                additionalAction.setItemType(ItemType.PLAYER_ITEM);
            }
            break;
            case SPEND_TO_STAT: {
                additionalAction = new SubActionModel(-1, false, data.getValue(), 0);
                additionalAction.setItemActionType(ItemActionType.GET_PLAYER);
                additionalAction.setItem(command.getToGenerateItem());
                additionalAction.setItemType(ItemType.STAT);
            }
            break;
            case GET_TO_INVIR:data.setValue(data.getValue() -changeValue);

        }
        if(data.getValue() == 0&&!data.getItem().isVisibleIfZero())
            dataMap.remove(data.getItem().getId());
        return additionalAction;
    }
}
