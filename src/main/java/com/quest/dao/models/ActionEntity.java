package com.quest.dao.models;

import java.util.ArrayList;

public class ActionEntity extends BaseEntity{

    public ActionEntity(int id, String description, int nodeId) {
        super(id, description);
        this.nodeId = nodeId;
        itemsRequirements = new ArrayList<>();
        statsRequirements = new ArrayList<>();
    }

    private ArrayList<RequirementEntity> itemsRequirements;

    private  ArrayList<RequirementEntity> statsRequirements;

    private int nodeId;

    public ArrayList<RequirementEntity> getItemsRequirements() {
        return itemsRequirements;
    }

    public void setItemsRequirements(ArrayList<RequirementEntity> itemsRequirements) {
        this.itemsRequirements = itemsRequirements;
    }

    public ArrayList<RequirementEntity> getStatsRequirements() {
        return statsRequirements;
    }

    public void setStatsRequirements(ArrayList<RequirementEntity> statsRequirements) {
        this.statsRequirements = statsRequirements;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
}
