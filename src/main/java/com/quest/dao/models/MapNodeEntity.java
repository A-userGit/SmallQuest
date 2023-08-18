package com.quest.dao.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapNodeEntity extends BaseEntity {

    private List<ActionEntity> actions;

    private boolean isFinale;

    public MapNodeEntity(int id, String description) {
        super(id, description);
        actions = new ArrayList<>();
    }

    public MapNodeEntity()
    {
        super();
        actions = new ArrayList<>();
    }

    public List<ActionEntity> getActions() {
        return actions;
    }

    public void setActions(List<ActionEntity> actions) {
        this.actions = actions;
    }

    public boolean isFinale() {
        return isFinale;
    }

    public void setFinale(boolean finale) {
        isFinale = finale;
    }
}
