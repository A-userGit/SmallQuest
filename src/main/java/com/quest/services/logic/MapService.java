package com.quest.services.logic;

import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.interfaces.ActionsDao;
import com.quest.dao.interfaces.ItemsDao;
import com.quest.dao.interfaces.MapDataDao;
import com.quest.services.models.ActionModel;
import com.quest.services.models.MapNode;

import java.util.HashMap;
import java.util.List;

public class MapService {

    private HashMap<Integer, ItemModel> items;

    private HashMap<Integer, ItemModel> statsMap;

    private List<MapNode> nodes;
    private String dataPath;
    public MapService(String dataPath)
    {
        this.dataPath = dataPath;
    }
    public MapNode loadMap()
    {
        LoaderService service = new LoaderService(dataPath);
        nodes = service.loadMapNodes();
        items = service.loadItems();
        statsMap  = service.loadStats();
    }
}
