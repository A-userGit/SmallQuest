package com.quest.services.logic;

import com.quest.commons.exceptions.DataException;
import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemPlace;
import com.quest.services.interfaces.IValidated;
import com.quest.services.interfaces.RequirementComparator;
import com.quest.services.logic.functions.NumFuncAnswer;
import com.quest.services.models.*;

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

    public void processSubActions(List<SubActionModel> subActions)
    {
        List<NumFuncAnswer> intermediateResults = new ArrayList<>();
        for (SubActionModel subAction: subActions) {
            Map<ItemPlace, Map> requiredData = getRequiredData(subAction);

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
        Set<ItemPlace> itemPlaces = data.keySet();
        for (ItemPlace place: itemPlaces) {
            Map map = data.get(place);
            subAction.getSourceConsumerPairs().
            DataSetterProvider.getDataSetter(,subAction.getActionDataType());

        }
    }

    public boolean makeTurn(int actionId)
    {
        List<ActionModel> actions = currentPosition.getActions();
        Optional<ActionModel> first = actions.stream().filter(s -> s.getId() == actionId).findFirst();
        ActionModel chosenAction = first.get();
        List<IValidated> currFailedRequirements = checkAllowedAction(chosenAction);
        if(!currFailedRequirements.isEmpty()) {
            failedRequirements = new ArrayList<>(currFailedRequirements);
            return false;
        }
        List<SubActionModel> subActions = chosenAction.getSubActions();
        List<SubActionModel> environmentActions = currentPosition.getEnvironmentActions();
        List<SubActionModel> allSubActions = new ArrayList<>(subActions);
        allSubActions.addAll(environmentActions);

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
