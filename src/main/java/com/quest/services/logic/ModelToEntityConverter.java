package com.quest.services.logic;

import com.quest.commons.interfaces.IdSupported;
import com.quest.dao.entities.AssignedItemEntity;
import com.quest.dao.entities.AssignedStatEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.entities.UserEntity;
import com.quest.services.models.LocalItem;
import com.quest.services.models.LocalStat;
import com.quest.services.models.MapNode;
import com.quest.services.models.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModelToEntityConverter {
    public static UserEntity getUserEntity(UserModel model)
    {
        UserEntity userEntity = new UserEntity(model.getId(), model.getDescription(), model.getPosition().getId());
        List<AssignedItemEntity> items = new ArrayList<>();
        for (LocalItem localItem:model.getInventory().values()) {
            items.add(getAssignedItemEntity(localItem));
        }
        userEntity.setInventoryItems(items);
        List<AssignedStatEntity> statEntities = new ArrayList<>();
        for (LocalStat localStat:model.getStats().values()) {
            statEntities.add(getAssignedStatEntity(localStat));
        }
        userEntity.setStats(statEntities);
        return userEntity;
    }

    public static AssignedItemEntity getAssignedItemEntity(LocalItem item)
    {
        AssignedItemEntity entity = new AssignedItemEntity(item.getId(), item.getItem().getId(),item.getValue());
        return entity;
    }

    public static AssignedStatEntity getAssignedStatEntity(LocalStat stat)
    {
        AssignedStatEntity entity = new AssignedStatEntity(stat.getId(), stat.getItem().getId(),stat.getAmount(),stat.isCritical());
        return entity;
    }

    public static<T extends IdSupported> List<Integer> getIdList(List<T> models)
    {
        List<Integer> idList = new ArrayList<>();
        for (T model:models) {
            idList.add(model.getId());
        }
        return idList;
    }

    public static MapNodeEntity getNodeEntity(MapNode mapNode)
    {
        MapNodeEntity entity = new MapNodeEntity(mapNode.getId(),mapNode.getDescription());
        Collection<LocalItem> values = mapNode.getItems().values();
        for (LocalItem item:values) {
            entity.getItemsHere().add(getAssignedItemEntity(item));
        }
        entity.setActions(getIdList(mapNode.getActions()));
        entity.setEnvironmentActions(getIdList(mapNode.getEnvironmentActions()));
        entity.setFinale(mapNode.isFinale());
        return entity;
    }
}
