package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;

public class BaseModel implements IdSupported {
    private int id;
    private String description;

    public BaseModel(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
