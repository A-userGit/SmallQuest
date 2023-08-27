package com.quest.services.models;

import com.quest.commons.models.BaseNodeModel;

import java.util.ArrayList;
import java.util.List;

public class MapNode extends BaseNodeModel {

    public MapNode(int id, String description) {
        super(id, description);
        actions = new ArrayList<>();
        items = new ArrayList<>();
        environmentActions = new ArrayList<>();
    }

    private List<ActionModel> actions;

    private List<LocalItem> items;

    private List<ActionModel> environmentActions;

    public List<ActionModel> getActions() {
        return actions;
    }

    public void setActions(List<ActionModel> actions) {
        this.actions = actions;
    }

    public List<LocalItem> getItems() {
        return items;
    }

    public void setItems(List<LocalItem> items) {
        this.items = items;
    }

    public List<ActionModel> getEnvironmentActions() {
        return environmentActions;
    }

    public void setEnvironmentActions(List<ActionModel> environmentActions) {
        this.environmentActions = environmentActions;
    }
}
