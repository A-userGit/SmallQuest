package com.quest.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class UserEntity {
    private int nodeId;

    private List<AssignedItemEntity> inventoryItems;

    private List<AssignedStatEntity> stats;

    public UserEntity(int nodeId) {
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

    public List<AssignedStatEntity> getStats() {
        return stats;
    }

    public void setStats(List<AssignedStatEntity> stats) {
        this.stats = stats;
    }
}
