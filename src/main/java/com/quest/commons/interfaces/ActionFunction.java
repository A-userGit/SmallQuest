package com.quest.commons.interfaces;

import java.util.List;

public interface ActionFunction<A,I,R> {

    public List<R> performChange(I itemData, A additionalData)
}
