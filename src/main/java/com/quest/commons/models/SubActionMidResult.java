package com.quest.commons.models;

import com.quest.commons.interfaces.IdSupported;

public class SubActionMidResult<T> implements IdSupported {
    private int id;
    private T data;

    public SubActionMidResult(int id, T data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
