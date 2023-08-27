package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.interfaces.IdSupported;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.entities.SubActionEntity;
import com.quest.services.models.ActionModel;
import com.quest.services.models.MapNode;
import com.quest.services.models.SubActionModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EntitiesToModelsConverter {

    public static MapNode getNodeModel(MapNodeEntity entity)
    {
        MapNode node = new MapNode(entity.getId(), entity.getDescription());
        return node;
    }

    public static SubActionModel getSubActionModel(SubActionEntity entity)
    {
        SubActionModel subActionModel = new SubActionModel(entity.getId(), entity.isRandom(),
                entity.getStartAmount(), entity.getRandomTopBorder());
        subActionModel.setSubActionType(entity.getSubActionType());
        return subActionModel;
    }

    public static ActionModel getActionModel(ActionEntity entity)
    {
        ActionModel  actionModel = new ActionModel(entity.getId(), entity.getDescription());
        actionModel.setActionType(entity.getActionType());
        return actionModel;
    }

    public static<T extends IdSupported> List<T> getListFromIds(List<Integer> idList, List<T> searchList) throws NoSuchItemException {
        ArrayList<T> resultList = new ArrayList<>();
        for (int id:idList) {
            Optional<T> first = searchList.stream().filter(s -> s.getId() == id).findFirst();
            if(first.isEmpty()) {
                String name = searchList.getClass().getComponentType().getCanonicalName();
                throw new NoSuchItemException(id, name);
            }
            resultList.add(first.get());
        }
        return resultList;
    }


}
