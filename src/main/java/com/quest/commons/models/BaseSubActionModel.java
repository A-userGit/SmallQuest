package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;

public class BaseSubActionModel implements IdSupported {

    private int id;

    private boolean random;

    private int startAmount;

    private int randomTopBorder;

    private ItemActionType subActionType;

    private ItemType itemType;

    public BaseSubActionModel(int id, boolean random, int startAmount, int randomTopBorder) {
        this.id = id;
        this.random = random;
        this.startAmount = startAmount;
        this.randomTopBorder = randomTopBorder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public int getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(int startAmount) {
        this.startAmount = startAmount;
    }

    public int getRandomTopBorder() {
        return randomTopBorder;
    }

    public void setRandomTopBorder(int randomTopBorder) {
        this.randomTopBorder = randomTopBorder;
    }

    public ItemActionType getSubActionType() {
        return subActionType;
    }

    public void setSubActionType(ItemActionType subActionType) {
        this.subActionType = subActionType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
