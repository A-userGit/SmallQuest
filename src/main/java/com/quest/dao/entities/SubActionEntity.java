package com.quest.dao.entities;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;

public class SubActionEntity {

    private int id;

    private boolean random;

    private int itemId;

    private int startAmount;

    private int randomTopBorder;

    private ItemActionType subActionType;

    private ItemType itemType;

    public SubActionEntity(int id, boolean random, int itemId, int startAmount, int randomTopBorder) {
        this.id = id;
        this.random = random;
        this.itemId = itemId;
        this.startAmount = startAmount;
        this.randomTopBorder = randomTopBorder;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
