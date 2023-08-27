package com.quest.services.models;

import com.quest.commons.models.BaseActionModel;

import java.util.ArrayList;

public class ActionModel extends BaseActionModel {

    public ActionModel(int id, String description) {
        super(id, description);
        itemsRequirements = new ArrayList<>();
        statsRequirements = new ArrayList<>();
        nodesToGo = new ArrayList<>();
        subActions = new ArrayList<>();
    }

    private ArrayList<RequirementModel> itemsRequirements;

    private  ArrayList<RequirementModel> statsRequirements;

    private ArrayList<MapNode> nodesToGo;

    private ArrayList<SubActionModel> subActions;

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

    public ArrayList<MapNode> getNodesToGo() {
        return nodesToGo;
    }

    public void setNodesToGo(ArrayList<MapNode> nodesToGo) {
        this.nodesToGo = nodesToGo;
    }

    public ArrayList<SubActionModel> getSubActions() {
        return subActions;
    }

    public void setSubActions(ArrayList<SubActionModel> subActions) {
        this.subActions = subActions;
    }
}
