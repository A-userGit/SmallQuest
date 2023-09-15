package com.quest.commons.models;

import java.util.ArrayList;
import java.util.List;

public class ContainerItemModel<T extends BaseContainerElement> extends ItemModel{

    private List<T> elements;

    public ContainerItemModel(int id, String description, boolean visibleIfZero, boolean infinite) {
        super(id, description, visibleIfZero, infinite);
        this.elements = new ArrayList<>();
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
