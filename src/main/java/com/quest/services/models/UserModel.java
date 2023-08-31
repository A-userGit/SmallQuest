package com.quest.services.models;

import com.quest.commons.models.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModel extends BaseModel {

    private MapNode position;

    private HashMap<Integer,LocalItem> inventory;

    private HashMap<Integer,LocalStat> stats;

    public UserModel(int id, String description) {
        super(id, description);
        inventory = new HashMap<>();
        stats = new HashMap<>();
    }

    public MapNode getPosition() {
        return position;
    }

    public void setPosition(MapNode position) {
        this.position = position;
    }

    public HashMap<Integer, LocalItem> getInventory() {
        return inventory;
    }

    public void setInventory(HashMap<Integer, LocalItem> inventory) {
        this.inventory = inventory;
    }

    public HashMap<Integer, LocalStat> getStats() {
        return stats;
    }

    public void setStats(HashMap<Integer, LocalStat> stats) {
        this.stats = stats;
    }
}
