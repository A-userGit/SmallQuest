package com.quest.commons.models;

import com.quest.dao.entities.RequirementEntity;

import java.util.ArrayList;

public class ActionModel extends BaseModel {

    public ActionModel(int id, String description, int nodeId) {
        super(id, description);
        itemsRequirements = new ArrayList<>();
        statsRequirements = new ArrayList<>();
    }

    private ArrayList<RequirementEntity> itemsRequirements;

    private  ArrayList<RequirementEntity> statsRequirements;

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
}
