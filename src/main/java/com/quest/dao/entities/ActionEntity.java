package com.quest.dao.entities;

import com.quest.commons.types.ActionType;
import com.quest.commons.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class ActionEntity extends BaseModel {

    private List<Integer> subActions;

    List<Integer> requirements;

    List<Integer> nodesToGo;

    private ActionType actionType;

    public ActionEntity(int id, String description, ActionType actionType) {
        super(id, description);
        this.actionType = actionType;
        requirements = new ArrayList<>();
        subActions = new ArrayList<>();
        nodesToGo = new ArrayList<>();
    }

    public List<Integer> getSubActions() {
        return subActions;
    }

    public void setSubActions(List<Integer> subActions) {
        this.subActions = subActions;
    }

    public List<Integer> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Integer> requirements) {
        this.requirements = requirements;
    }

    public List<Integer> getNodesToGo() {
        return nodesToGo;
    }

    public void setNodesToGo(List<Integer> nodesToGo) {
        this.nodesToGo = nodesToGo;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
