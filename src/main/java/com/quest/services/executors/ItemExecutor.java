package com.quest.services.executors;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.Executor;
import com.quest.services.models.LocalItem;
import com.quest.services.models.SubActionModel;

import java.util.HashMap;

public class ItemExecutor implements Executor<LocalItem, SubActionModel> {

    @Override
    public SubActionModel execute(HashMap<Integer,LocalItem> dataMap, SubActionModel command) {
        SubActionModel additionalAction = null;
        LocalItem data = null
        if(command.getSubActionType() == ItemActionType.GET_PLAYER||command.getSubActionType() == ItemActionType.GET_PLAYER_INF)
        {
            data = new LocalItem(0);
            data.setItem(command.getItem());
            data.
        }

            data = dataMap.get(command.getItem().getId());
        int changeValue = command.getStartAmount();
        if(command.isRandom())
         changeValue = (int) Math.round(Math.random()*(command.getRandomTopBorder()-changeValue)
         +changeValue);
        ItemActionType subActionType = command.getSubActionType();
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
            case SPEND_TO_INVENT: {
                additionalAction = new SubActionModel(-1, false, data.getValue(), 0);
                additionalAction.setSubActionType(ItemActionType.GET_PLAYER);
                additionalAction.setItem(command.getToGenerateItem());
                additionalAction.setItemType(ItemType.PLAYER_ITEM);
            }
            break;
            case SPEND_TO_STAT: {
                additionalAction = new SubActionModel(-1, false, data.getValue(), 0);
                additionalAction.setSubActionType(ItemActionType.GET_PLAYER);
                additionalAction.setItem(command.getToGenerateItem());
                additionalAction.setItemType(ItemType.STAT);
            }
            break;
            case GET_TO_INVIR:data.setValue(data.getValue() -changeValue);

        }
        if(data.getValue() == 0&&!data.getItem().isVisibleIfZero())
            dataMap.remove()
        return additionalAction;
    }

    @Override
    public SubActionModel execute(LocalItem data, SubActionModel command) {
        return null;
    }
}
