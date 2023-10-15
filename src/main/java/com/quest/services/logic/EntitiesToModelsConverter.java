package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.interfaces.IdSupported;
import com.quest.commons.interfaces.ItemIdSupported;
import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.ContainerItemModel;
import com.quest.commons.models.ItemModel;
import com.quest.dao.entities.*;
import com.quest.services.models.*;

import java.util.*;
import java.util.function.Function;

public class EntitiesToModelsConverter {

    public static MapNode getNodeModel(MapNodeEntity entity)
    {
        MapNode node = new MapNode(entity.getId(), entity.getDescription());
        return node;
    }

    public static SubActionModel getSubActionModel(SubActionEntity entity)
    {
        SubActionModel subActionModel = new SubActionModel(entity.getId());
        subActionModel.setItemActionType(entity.getItemActionType());
        subActionModel.setActionDataType(entity.getActionDataType());
        subActionModel.setActionFunctionType(entity.getActionFunctionType());
        subActionModel.setChangeData(entity.getChangeData());
        subActionModel.setSourceConsumerPairs(entity.getSourceConsumerPairs());
        subActionModel.setItemPlace(entity.getItemPlace());
        subActionModel.setChangeFunction(FunctionProvider.getFunction(entity.getActionFunctionType()));
        subActionModel.setItemId(entity.getItemId());
        return subActionModel;
    }

    public static ActionModel getActionModel(ActionEntity entity)
    {
        ActionModel  actionModel = new ActionModel(entity.getId(), entity.getDescription());
        actionModel.setActionType(entity.getActionType());
        actionModel.setActionRole(entity.getActionRole());
        actionModel.setTurnsActive(entity.getTurnsActive());
        return actionModel;
    }

    public static RequirementModel getRequirementModel(RequirementEntity entity)
    {
        RequirementModel model = new RequirementModel(entity.getId(),entity.getDescription(), entity.getValue(), entity.isBlocker());
        model.setRequirementType(entity.getRequirementType());
        model.setItemPlace(entity.getItemPlace());
        model.setRestrictionType(entity.getRestrictionType());
        return model;
    }

    public static UserModel getUserModel(UserEntity entity)
    {
        UserModel userModel = new UserModel(entity.getId(), entity.getDescription());
        return userModel;
    }

    public static LocalItem getLocalItem(AssignedItemEntity entity)
    {
        LocalItem localItem = new LocalItem(entity.getAmount());
        localItem.setMaxAmount(entity.getMaxAmount());
        return localItem;
    }

    public static LocalStat getLocalStat(AssignedStatEntity entity)
    {
        LocalStat localStat = new LocalStat(entity.getId(),entity.getAmount(),entity.isCritical());
        localStat.setMaxStat(entity.getMaxStat());
        return localStat;
    }

    public static ContainerItemModel<ItemContainerElement> getContainerItem(ContainerItemModel<ContainerIdElement> entity)
    {
        ContainerItemModel<ItemContainerElement> containerItem = new ContainerItemModel<>(entity.getId(), entity.getDescription(),
        entity.isVisibleIfZero(), entity.isInfinite());
        containerItem.setType(entity.getType());
        return containerItem;
    }

    public static ItemContainerElement getContainerElement(ContainerIdElement idElement)
    {
        ItemContainerElement element = new ItemContainerElement();
        element.setQuantity(element.getQuantity());
        return element;
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

    public static<T extends ItemModel,R extends ItemSupported,S extends ItemIdSupported> HashMap<Integer,R> getMapFromIds(List<S> idList, HashMap<Integer,T> searchList, Function<S,R> transform) throws NoSuchItemException {
        HashMap<Integer, R> resultList = new HashMap<>();
        for (S idItem:idList) {
           T first = searchList.get(idItem.getItemId());
            if(first == null) {
                String name = searchList.getClass().getComponentType().getCanonicalName();
                throw new NoSuchItemException(idItem.getItemId(), name);
            }
            R result = transform.apply(idItem);
            result.setItem(first);
            resultList.put(idItem.getItemId(),result);
        }
        return resultList;
    }
}
