package com.quest.commons.models;

import com.quest.commons.types.ActionType;

public class BaseActionModel extends BaseModel{
    private ActionType actionType;

    public BaseActionModel(int id, String description) {
        super(id, description);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
