package com.quest.dao.entities;

 import com.quest.commons.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class MapNodeEntity extends BaseModel {

    private List<AssignedItemEntity> itemsHere;

    private List<Integer> actionIds;

    private List<Integer> environmentActions;

    public MapNodeEntity(int id, String description) {
        super(id, description);
        itemsHere = new ArrayList<>();
        actionIds = new ArrayList<>();
        environmentActions = new ArrayList<>();
    }

    public List<Integer> getActions() {
        return actionIds;
    }

    public void setActions(List<Integer> actions) {
        this.actionIds = actions;
    }

    public List<AssignedItemEntity> getItemsHere() {
        return itemsHere;
    }

    public void setItemsHere(List<AssignedItemEntity> itemsHere) {
        this.itemsHere = itemsHere;
    }

    public void setEnvironmentActions(List<Integer> environmentActions) {
        this.environmentActions = environmentActions;
    }

    public List<Integer> getEnvironmentActions() {
        return environmentActions;
    }
}
