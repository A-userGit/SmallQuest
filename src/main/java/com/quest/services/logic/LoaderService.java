package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.commons.types.ItemType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.entities.RequirementEntity;
import com.quest.dao.entities.SubActionEntity;
import com.quest.dao.interfaces.*;
import com.quest.dao.repositries.*;
import com.quest.services.models.ActionModel;
import com.quest.services.models.MapNode;
import com.quest.services.models.RequirementModel;
import com.quest.services.models.SubActionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.quest.services.logic.EntitiesToModelsConverter.getListFromIds;

public class LoaderService {

    private ItemsDao itemsDao;
    private MapDataDao mapDataDao;
    private SubActionsDao subActionsDao;
    private ActionsDao actionsDao;

    private RequirementsDao requirementsDao;

    public LoaderService(String dataPath)
    {
        itemsDao = new ItemsRepository(dataPath);
        subActionsDao = new SubActionRepository(dataPath);
        actionsDao = new ActionsRepository(dataPath);
        requirementsDao = new ReqirementsRepository(dataPath);
    }
    public HashMap<Integer,ItemModel> loadItems()
    {
        itemsDao.setItemDaoType(ItemDaoType.ITEM);
        HashMap<Integer,ItemModel> itemMap = new HashMap<>();
        List<ItemModel> list = itemsDao.getList();
        for (ItemModel model:list) {
            itemMap.put(model.getId(),model);
        }
        return itemMap;
    }

    public HashMap<Integer,ItemModel> loadStats()
    {
        itemsDao.setItemDaoType(ItemDaoType.STAT);
        HashMap<Integer,ItemModel> statsMap = new HashMap<>();
        List<ItemModel> list = itemsDao.getList();
        for (ItemModel model:list) {
            statsMap.put(model.getId(),model);
        }
        return statsMap;
    }

    public List<MapNode> loadMapNodes(List<SubActionModel> subActions, List<ActionModel> actions) throws NoSuchItemException {
        List<MapNode> listResult = new ArrayList<>();
        List<MapNodeEntity> list = mapDataDao.getList();
        for (MapNodeEntity entity: list) {
            MapNode nodeModel = EntitiesToModelsConverter.getNodeModel(entity);
            List<SubActionModel> nodeSubActions = EntitiesToModelsConverter.getListFromIds(entity.getEnvironmentActions(), subActions);
            List<ActionModel> nodeActions = EntitiesToModelsConverter.getListFromIds(entity.getActions(), actions);
            nodeModel.setActions(nodeActions);
            nodeModel.setEnvironmentActions(nodeSubActions);
            listResult.add(nodeModel);
        }
        return listResult;
    }

    public List<SubActionModel> loadSubAction(HashMap<Integer,ItemModel> items) throws NoSuchItemException {
        List<SubActionEntity> list = subActionsDao.getList();
        List<SubActionModel> results = new ArrayList<>();
        for (SubActionEntity entity: list) {
            SubActionModel subActionModel = EntitiesToModelsConverter.getSubActionModel(entity);
            ItemModel itemModel = items.get(entity.getItemId());
            if(itemModel == null) {
                String name = items.getClass().getComponentType().getCanonicalName();
                throw new NoSuchItemException(entity.getItemId(), name);
            }
            subActionModel.setItem(itemModel);
            results.add(subActionModel);
        }
        return results;
    }

    public List<ActionModel> loadActions(List<SubActionModel> subActionsList, List<RequirementModel> requirements) throws NoSuchItemException {
        List<ActionModel> actionModels = new ArrayList<>();
        List<ActionEntity> list = actionsDao.getList();
        for (ActionEntity entity:list) {

            ActionModel actionModel = EntitiesToModelsConverter.getActionModel(entity);
            List<SubActionModel> subActions = EntitiesToModelsConverter.getListFromIds(entity.getSubActions(),subActionsList);
            List<RequirementModel> requirementList = getListFromIds(entity.getRequirements(), requirements);
            actionModel.setSubActions(subActions);
            List<RequirementModel> statsReq = new ArrayList<>();
            List<RequirementModel> itemsReq = new ArrayList<>();
            for (RequirementModel requirement:requirementList) {
                if(requirement.getItemType() == ItemType.STAT)
                    statsReq.add(requirement);
                else
                    itemsReq.add(requirement);
            }
            actionModel.setItemsRequirements(itemsReq);
            actionModel.setStatsRequirements(statsReq);
            actionModels.add(actionModel);
        }
        return actionModels;
    }

    public List<RequirementModel> loadRequirements(HashMap<Integer,ItemModel> items, HashMap<Integer, ItemModel> stats) throws NoSuchItemException {
        List<RequirementModel> actionModels = new ArrayList<>();
        List<RequirementEntity> list = requirementsDao.getList();
        for (RequirementEntity entity:list) {
            RequirementModel requirementModel = EntitiesToModelsConverter.getRequirementModel(entity);
            ItemModel itemModel = null;
            if(requirementModel.getItemType() == ItemType.ITEM)
                 itemModel = items.get(entity.getItemId());
            else
                itemModel = stats.get(entity.getItemId());
            if(itemModel == null) {
                String name = items.getClass().getComponentType().getCanonicalName();
                throw new NoSuchItemException(entity.getItemId(), name);
            }
            requirementModel.setItem(itemModel);
            actionModels.add(requirementModel);
        }
        return actionModels;
    }
}
