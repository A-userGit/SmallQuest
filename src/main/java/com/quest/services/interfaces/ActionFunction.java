package com.quest.services.interfaces;

import java.util.List;

public interface ActionFunction<I,T,R> {

    public List<R> performChange(I itemData, T actionData);
}
