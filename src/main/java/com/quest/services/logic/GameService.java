package com.quest.services.logic;

import com.quest.commons.exceptions.DataException;
import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemType;
import com.quest.services.comparators.ItemQuantityComparator;
import com.quest.services.comparators.StatComparator;
import com.quest.services.interfaces.IValidated;
import com.quest.services.interfaces.RequirementComparator;
import com.quest.services.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class GameService {

    private String dataPath;

    private MapNode currentPosition;

    private List<ActionModel> currentActions;

    private HashMap<Integer, ItemModel> items;

    private HashMap<Integer, ItemModel> stats;

    private List<MapNode> nodes;

    private UserModel currentPlayer;

    private List<RequirementModel> failedRequirements;

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

    private List<RequirementModel> checkAllowedAction(IValidated actionToValidate)
    {
        ActionModel action = (ActionModel) actionToValidate;
        List<RequirementModel> rejectedRequirements = new ArrayList<>();
        boolean valid = true;
        List<RequirementModel> statsRequirements = action.getStatsRequirements();
        for (RequirementModel statsReq:statsRequirements) {
            valid = statsReq.isValid(currentPlayer.getStats(),new StatComparator());
            if(!valid)
                rejectedRequirements.add(statsReq);
        }
        List<RequirementModel> itemsRequirements = action.getItemsRequirements();
        for (RequirementModel itemReq:itemsRequirements) {
            RequirementComparator comparator = new ItemQuantityComparator();
            switch (itemReq.getItemType())
            {
                case ITEM: valid = itemReq.isValid(currentPosition.getItems(), comparator);
                break;
                case PLAYER_ITEM: valid = itemReq.isValid(currentPlayer.getInventory(), comparator);
                break;
            }
            if(!valid) {
                rejectedRequirements.add(itemReq);
            }
        }
        return rejectedRequirements;
    }

    public void processSubActions(List<SubActionModel> subActions)
    {
        for (SubActionModel subAction: subActions) {
            if
        }
    }

    public boolean makeTurn(int actionId)
    {
        List<ActionModel> actions = currentPosition.getActions();
        Optional<ActionModel> first = actions.stream().filter(s -> s.getId() == actionId).findFirst();
        ActionModel chosenAction = first.get();
        List<RequirementModel> currFailedRequirements = checkAllowedAction(chosenAction);
        if(!currFailedRequirements.isEmpty()) {
            failedRequirements = new ArrayList<>(currFailedRequirements);
            return false;
        }
        List<SubActionModel> subActions = chosenAction.getSubActions();
        List<SubActionModel> environmentActions = currentPosition.getEnvironmentActions();
        List<SubActionModel> allSubActions = new ArrayList<>(subActions);
        allSubActions.addAll(environmentActions);

    }

}
