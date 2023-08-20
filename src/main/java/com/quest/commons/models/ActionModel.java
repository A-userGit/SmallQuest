package com.quest.commons.models;

import com.quest.dao.entities.RequirementModel;

import java.util.ArrayList;

public class ActionModel extends BaseModel {

    public ActionModel(int id, String description, int nodeId) {
        super(id, description);
        this.nodeId = nodeId;
        itemsRequirements = new ArrayList<>();
        statsRequirements = new ArrayList<>();
    }

    private ArrayList<RequirementModel> itemsRequirements;

    private  ArrayList<RequirementModel> statsRequirements;

    public ArrayList<RequirementModel> getItemsRequirements() {
        return itemsRequirements;
    }

    public void setItemsRequirements(ArrayList<RequirementModel> itemsRequirements) {
        this.itemsRequirements = itemsRequirements;
    }

    public ArrayList<RequirementModel> getStatsRequirements() {
        return statsRequirements;
    }

    public void setStatsRequirements(ArrayList<RequirementModel> statsRequirements) {
        this.statsRequirements = statsRequirements;
    }
}
