package com.quest.services.models;

import com.quest.commons.models.BaseActionModel;

import java.util.ArrayList;
import java.util.List;

public class ActionModel extends BaseActionModel {

    public ActionModel(int id, String description) {
        super(id, description);
        itemsRequirements = new ArrayList<>();
        statsRequirements = new ArrayList<>();
        nodesToGo = new ArrayList<>();
        subActions = new ArrayList<>();
    }

    private List<RequirementModel> itemsRequirements;

    private  List<RequirementModel> statsRequirements;

    private List<MapNode> nodesToGo;

    private List<SubActionModel> subActions;

    public List<RequirementModel> getItemsRequirements() {
        return itemsRequirements;
    }

    public void setItemsRequirements(List<RequirementModel> itemsRequirements) {
        this.itemsRequirements = itemsRequirements;
    }

    public List<RequirementModel> getStatsRequirements() {
        return statsRequirements;
    }

    public void setStatsRequirements(List<RequirementModel> statsRequirements) {
        this.statsRequirements = statsRequirements;
    }

    public List<MapNode> getNodesToGo() {
        return nodesToGo;
    }

    public void setNodesToGo(List<MapNode> nodesToGo) {
        this.nodesToGo = nodesToGo;
    }

    public List<SubActionModel> getSubActions() {
        return subActions;
    }

    public void setSubActions(List<SubActionModel> subActions) {
        this.subActions = subActions;
    }
}
