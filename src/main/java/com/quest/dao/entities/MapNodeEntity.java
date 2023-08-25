package com.quest.dao.entities;

 import com.quest.commons.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class MapNodeEntity extends BaseModel {

    private List<Integer> itemsHere;

    private boolean isFinale;
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

    public boolean isFinale() {
        return isFinale;
    }

    public void setFinale(boolean finale) {
        isFinale = finale;
    }

    public List<Integer> getItemsHere() {
        return itemsHere;
    }

    public void setItemsHere(List<Integer> itemsHere) {
        this.itemsHere = itemsHere;
    }

    public void setEnvironmentActions(List<Integer> environmentActions) {
        this.environmentActions = environmentActions;
    }

    public List<Integer> getEnvironmentActions() {
        return environmentActions;
    }
}
