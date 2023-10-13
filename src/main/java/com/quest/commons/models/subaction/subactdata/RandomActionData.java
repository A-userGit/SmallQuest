package com.quest.commons.models.subaction.subactdata;

public class RandomActionData implements ActionDataInterface{

    private int topBorder;
    private int floorBorder;

    public RandomActionData(int topBorder, int floorBorder) {
        this.topBorder = topBorder;
        this.floorBorder = floorBorder;
    }

    public int getTopBorder() {
        return topBorder;
    }

    public void setTopBorder(int topBorder) {
        this.topBorder = topBorder;
    }

    public int getFloorBorder() {
        return floorBorder;
    }

    public void setFloorBorder(int floorBorder) {
        this.floorBorder = floorBorder;
    }
}
