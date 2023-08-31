package com.quest.dao.entities;

import com.quest.commons.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class UserEntity extends BaseModel {
    private int nodeId;

    private List<AssignedItemEntity> inventoryItems;

    private List<AssignedLocalStatEntity> stats;

    public UserEntity(int id, String name, int nodeId) {
        super(id, name);
        this.nodeId = nodeId;
        this.inventoryItems = new ArrayList<>();
        this.stats = new ArrayList<>();
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public List<AssignedItemEntity> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(List<AssignedItemEntity> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public List<AssignedLocalStatEntity> getStats() {
        return stats;
    }

    public void setStats(List<AssignedLocalStatEntity> stats) {
        this.stats = stats;
    }
}
