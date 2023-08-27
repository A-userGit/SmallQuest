package com.quest.services.logic;

import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.interfaces.ActionsDao;
import com.quest.dao.interfaces.ItemsDao;
import com.quest.dao.interfaces.MapDataDao;
import com.quest.services.models.ActionModel;
import com.quest.services.models.MapNode;

import java.util.List;

public class MapService {

    private MapDataDao mapDataDao;

    private ActionsDao actionsDao;
    
    private ItemsDao itemsDao;

    public void loadMap()
    {
        List<MapNodeEntity> list = mapDataDao.getList();
        List<ItemModel> itemModels = loadItems();
        for (MapNodeEntity entity: list) {
            MapNode nodeModel = EntitiesToModelsConverter.getNodeModel(entity);

            EntitiesToModelsConverter.getListFromIds(entity.getActions(),)
        }
    }

    public List<ActionModel> getActions()
    {
        List<ActionEntity> list = actionsDao.getList();
        for (ActionEntity actionEntity:list) {
            actionEntity.
        }
    }
    
    public List<ItemModel> loadItems()
    {
        itemsDao.setItemDaoType(ItemDaoType.ITEM);
        List<ItemModel> list = itemsDao.getList();
        return list;
    }

    public List<ItemModel> loadStats()
    {
        itemsDao.setItemDaoType(ItemDaoType.STAT);
        List<ItemModel> list = itemsDao.getList();
        return list;
    }
}
