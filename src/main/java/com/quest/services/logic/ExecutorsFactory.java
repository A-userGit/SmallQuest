package com.quest.services.logic;

import com.quest.commons.types.ItemPlace;
import com.quest.services.executors.ItemExecutor;
import com.quest.services.executors.StatExecutor;
import com.quest.services.interfaces.Executor;

public class ExecutorsFactory {
    public Executor getExecutor(ItemPlace type)
    {
        switch (type)
        {
            case ITEM : return new ItemExecutor();
            case STAT: return new StatExecutor();
            case PLAYER_ITEM: return new ItemExecutor();
        }
        return null;
    }
}
