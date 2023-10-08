package com.quest.services.interfaces;

import com.quest.commons.types.ItemPlace;

import java.util.List;
import java.util.Map;

public interface Executor<T,K > {
    public K execute(T data, K command);

    public List<ItemPlace> getNeededAdditionalCollections();

    public void setNeededCollections(Map<ItemPlace,Map> collections);

}
