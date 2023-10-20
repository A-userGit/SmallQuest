package com.quest.services.logic;

import com.quest.commons.exceptions.DataException;
import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.interfaces.ItemSupported;
import com.quest.commons.models.ItemModel;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.subaction.subactdata.ActionDataInterface;
import com.quest.commons.types.*;
import com.quest.services.interfaces.DataSetter;
import com.quest.services.interfaces.FuncResult;
import com.quest.services.interfaces.IValidated;
import com.quest.services.interfaces.RequirementComparator;
import com.quest.services.models.ActionModel;
import com.quest.services.models.MapNode;
import com.quest.services.models.SubActionModel;
import com.quest.services.models.UserModel;

import java.util.*;

public class GameService {

    private String dataPath;

    private MapNode currentPosition;

    private List<ActionModel> currentActions;

    private HashMap<Integer, ItemModel> items;

    private HashMap<Integer, ItemModel> stats;

    private List<MapNode> nodes;

    private UserModel currentPlayer;

    private List<IValidated> failedRequirements;

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public void startGame() throws DataException {
        MapService mapService = new MapService(dataPath);
        try{
            mapService.loadMap();
        }catch (NoSuchItemException e){
            throw new DataException(e, MapService.class.getSimpleName());
        }
        nodes = mapService.getNodes();
        items = mapService.getItems();
        stats = mapService.getStats();
        UserService userService = new UserService(dataPath);
        try {
            currentPlayer = userService.getDefaultUser(nodes, items, stats);
        } catch (NoSuchItemException e) {
            throw new DataException(e, UserService.class.getSimpleName());
        }
        currentPosition = nodes.get(0);
    }

    private List<IValidated> checkRequirements(List<IValidated> requirements, HashMap items)
    {
        List<IValidated> rejectedRequirements = new ArrayList<>();
        boolean valid;
        for (IValidated requirement:requirements) {
            RequirementComparator comparator = ReqComparatorsProvider.getComparator(requirement.getItemType(), requirement.getType());
            valid = requirement.isValid(items, comparator);
            if(!valid)
                rejectedRequirements.add(requirement);
        }
        return rejectedRequirements;
    }

    protected void setItemToSubAction(SubActionModel subAction)
    {
        ItemSupported item = null;
        if(subAction.getItemId()<=-1)
            return;
        switch (subAction.getItemPlace())
        {
            case STAT -> item = this.currentPlayer.getStats().get(subAction.getItemId());
            case ITEM -> item = this.currentPosition.getItems().get(subAction.getItemId());
            case PLAYER_ITEM -> item = this.currentPlayer.getInventory().get(subAction.getItemId());
        }
        subAction.setItem(item);
    }

    public void processSubActions(List<SubActionModel> subActions)
    {
        List<FuncResult> intermediateResults = new ArrayList<>();
        for (SubActionModel subAction: subActions) {
            Map<ItemPlace, Map> requiredData = getRequiredData(subAction);
            setSubActionData(subAction,requiredData);
            setItemToSubAction(subAction);

        }
    }

    protected Map<ItemPlace,Map> getRequiredData(SubActionModel subAction)
    {
        Map<ItemPlace, Map> requiredData = new HashMap<ItemPlace,Map>();
        Set<ActionItemFieldConnector> actionItemFieldConnectors = subAction.getSourceConsumerPairs().keySet();
        for (ActionItemFieldConnector place : actionItemFieldConnectors) {
            switch (place.getPlace())
            {
                case STAT-> {
                    if(!requiredData.containsKey(ItemPlace.STAT))
                        requiredData.put(ItemPlace.STAT, currentPlayer.getStats());
                }
                case ITEM -> {
                    if(!requiredData.containsKey(ItemPlace.ITEM))
                        requiredData.put(ItemPlace.ITEM, items);
                }
                case PLAYER_ITEM -> {
                    if(!requiredData.containsKey(ItemPlace.PLAYER_ITEM))
                        requiredData.put(ItemPlace.STAT, currentPlayer.getInventory());
                }
            }
        }
        return requiredData;
    }

    protected void setSubActionData(SubActionModel subAction, Map<ItemPlace, Map> data)
    {
        DataSetterProvider provider = new DataSetterProvider();
        Set<ItemPlace> itemPlaces = data.keySet();
        for (ItemPlace place: itemPlaces) {
            Map map = data.get(place);
            for(int i = 0; i<ItemType.values().length; i++)
            {
                ItemType itemType = ItemType.values()[i];
                for(int j = 0; j< ActionConnectorType.values().length; j++) {
                    ActionConnectorType connectorType = ActionConnectorType.values()[j];
                    List<ActionItemFieldConnector> keys = subAction.getSourceConsumerPairs().keySet().
                            stream().filter(k -> k.getPlace() == place && k.getType() == itemType
                            &&k.getConnectorType() == connectorType).toList();
                    if (keys.size() == 0)
                        continue;
                    DataSetter dataSetter = provider.getDataSetter(itemType, subAction.getActionDataType(), connectorType);
                    ActionDataInterface aData = dataSetter.setData(map, subAction.getChangeData(), subAction.getSourceConsumerPairs(), keys);
                    subAction.setChangeData(aData);
                }
            }
        }
    }

    protected boolean processAction(ActionModel action)
    {
        List<IValidated> currFailedRequirements = checkAllowedAction(action);
        if(!currFailedRequirements.isEmpty()) {
            if(action.getActionRole() == ActionRole.ACTIVE)
                failedRequirements = new ArrayList<>(currFailedRequirements);
            return false;
        }
        List<SubActionModel> subActions = action.getSubActions();
        processSubActions(subActions);
    }

    public boolean makeTurn(int actionId)
    {
        currentActions.stream().dropWhile(a->checkAllowedAction(a).stream().filter(v->v.getType() == RequirementType.SIMPLE).toList().isEmpty());
        for (ActionModel action:currentActions) {
            action.setTurnsActive(action.getTurnsActive()-1);
            processAction(action);
            if(action.)
        }
        List<ActionModel> actions = currentPosition.getActions();
        Optional<ActionModel> first = actions.stream().filter(s -> s.getId() == actionId).findFirst();
        ActionModel chosenAction = first.get();


    }

    protected List<IValidated> checkAllowedAction(ActionModel chosenAction) {
        List<IValidated> rejectedRequirements;
        List<IValidated> itemsRequirements = new ArrayList<>(chosenAction.getItemsRequirements());
        List<IValidated> inventoryReq = itemsRequirements.stream().filter(p -> p.getPlace() == ItemPlace.PLAYER_ITEM).toList();
        rejectedRequirements = checkRequirements(inventoryReq, currentPlayer.getInventory());
        List<IValidated> statsRequirements = new ArrayList<>(chosenAction.getStatsRequirements());
        List<IValidated> rejectedStats = checkRequirements(statsRequirements, currentPlayer.getStats());
        rejectedRequirements.addAll(rejectedStats);
        List<IValidated> placeItemsReq = itemsRequirements.stream().filter(p -> p.getPlace() == ItemPlace.ITEM).toList();
        List<IValidated> rejectedPlaceItems = checkRequirements(placeItemsReq, currentPosition.getItems());
        rejectedRequirements.addAll(rejectedPlaceItems);
        return rejectedRequirements;
    }

}
