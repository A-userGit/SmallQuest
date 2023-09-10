package com.quest.services.executors;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;
import com.quest.services.interfaces.Executor;
import com.quest.services.models.LocalStat;
import com.quest.services.models.SubActionModel;

public class StatExecutor implements Executor<LocalStat, SubActionModel> {
    @Override
    public SubActionModel execute(LocalStat data, SubActionModel command) {
        SubActionModel futureAction = null;
        int changeValue = command.getStartAmount();
        if(command.isRandom())
            changeValue = (int) Math.round(Math.random()*(command.getRandomTopBorder()-changeValue)
                    +changeValue);
        switch (command.getSubActionType()) {
            case SPEND:
                data.setAmount(data.getAmount() - changeValue);
                break;
            case GET_PLAYER:
                data.setAmount(data.getAmount() + changeValue);
                break;
            case GET_PLAYER_INF:
                data.setAmount(data.getMaxStat());
                break;
        }
        return futureAction;
    }
}
