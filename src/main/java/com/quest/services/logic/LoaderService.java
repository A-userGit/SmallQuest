package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.entities.SubActionEntity;
import com.quest.dao.interfaces.ActionsDao;
import com.quest.dao.interfaces.ItemsDao;
import com.quest.dao.interfaces.MapDataDao;
import com.quest.dao.interfaces.SubActionsDao;
import com.quest.dao.repositries.ActionsRepository;
import com.quest.dao.repositries.ItemsRepository;
import com.quest.dao.repositries.MapDataRepository;
import com.quest.dao.repositries.SubActionRepository;
import com.quest.services.models.ActionModel;
import com.quest.services.models.MapNode;
import com.quest.services.models.RequirementModel;
import com.quest.services.models.SubActionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoaderService {

    private ItemsDao itemsDao;
    private MapDataDao mapDataDao;
    private SubActionsDao subActionsDao;
    private ActionsDao actionsDao;

    public LoaderService(String dataPath)
    {
        itemsDao = new ItemsRepository(dataPath);
        mapDataDao = new MapDataRepository(dataPath);
        subActionsDao = new SubActionRepository(dataPath);
        actionsDao = new ActionsRepository(dataPath);
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

    public List<MapNode> loadMapNodes()
    {
        List<MapNodeEntity> list = mapDataDao.getList();
        for (MapNodeEntity entity: list) {
            MapNode nodeModel = EntitiesToModelsConverter.getNodeModel(entity);
            entity.
        }
    }

    public List<SubActionModel> loadSubAction(List<ItemModel> items) throws NoSuchItemException {
        List<SubActionEntity> list = subActionsDao.getList();
        List<SubActionModel> results = new ArrayList<>();
        for (SubActionEntity entity: list) {
            SubActionModel subActionModel = EntitiesToModelsConverter.getSubActionModel(entity);
            Optional<ItemModel> first = items.stream().filter(s -> s.getId() == entity.getItemId()).findFirst();
            if(first.isEmpty()) {
                String name = items.getClass().getComponentType().getCanonicalName();
                throw new NoSuchItemException(entity.getItemId(), name);
            }
            subActionModel.setItem(first.get());
            results.add(subActionModel);
        }
        return results;
    }

    public List<ActionModel> loadActions(List<SubActionModel> subActions, List<MapNode> nodes, List<RequirementModel> requirements) throws NoSuchItemException {
        List<ActionModel> actionModels = new ArrayList<>();
        List<ActionEntity> list = actionsDao.getList();
        for (ActionEntity entity:list) {
            ActionModel actionModel = EntitiesToModelsConverter.getActionModel(entity);
            List<MapNode> nodesList = EntitiesToModelsConverter.getListFromIds(entity.getNodesToGo(), nodes);
            EntitiesToModelsConverter.getListFromIds(entity.getStatsRequirements(), );
            actionModel.setNodesToGo(nodesList);
        }
    }
}
