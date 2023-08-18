package com.quest.dao.models;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private int id;
    private String description;

    public BaseEntity(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public BaseEntity()
    {}

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
