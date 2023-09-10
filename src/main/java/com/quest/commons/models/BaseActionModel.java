package com.quest.commons.models;

import com.quest.commons.types.ActionRole;
import com.quest.commons.types.ActionType;

public class BaseActionModel extends BaseModel{
    private ActionType actionType;

    private ActionRole actionRole;

    private int turnsActive;

    public BaseActionModel(int id, String description) {
        super(id, description);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public ActionRole getActionRole() {
        return actionRole;
    }

    public void setActionRole(ActionRole actionRole) {
        this.actionRole = actionRole;
    }

    public int getTurnsActive() {
        return turnsActive;
    }

    public void setTurnsActive(int turnsActive) {
        this.turnsActive = turnsActive;
    }
}
