package com.quest.dao.entities;

 import com.quest.commons.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class MapNodeEntity extends BaseModel {

    private List<Integer> userActionIds;

    private List<Integer> itemsHere;

    private boolean isFinale;

    public MapNodeEntity(int id, String description) {
        super(id, description);
        userActionIds = new ArrayList<>();
    }

    public List<Integer> getActions() {
        return userActionIds;
    }

    public void setActions(List<Integer> actions) {
        this.userActionIds = actions;
    }

    public boolean isFinale() {
        return isFinale;
    }

    public void setFinale(boolean finale) {
        isFinale = finale;
    }
}
