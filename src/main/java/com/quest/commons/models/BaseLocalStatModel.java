package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;

public class BaseLocalStatModel implements IdSupported{

    private int id;
    private int amount;
    private boolean critical;

    private int maxStat;

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMaxStat() {
        return maxStat;
    }

    public void setMaxStat(int maxStat) {
        this.maxStat = maxStat;
    }

    public BaseLocalStatModel(int id, int amount, boolean critical) {
        this.id = id;
        this.amount = amount;
        this.critical = critical;
    }
}
