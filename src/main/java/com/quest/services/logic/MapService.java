package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.AssignedItemEntity;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.interfaces.ActionsDao;
import com.quest.dao.interfaces.ItemsDao;
import com.quest.dao.interfaces.MapDataDao;
import com.quest.dao.repositries.MapDataRepository;
import com.quest.services.models.*;

import java.util.*;
import java.util.stream.Stream;

public class MapService {

    private HashMap<Integer, ItemModel> items;

    private HashMap<Integer, ItemModel> statsMap;

    private List<MapNode> nodes;

    private  MapDataDao mapDataDao;
    private String dataPath;
    public MapService(String dataPath)
    {
        this.dataPath = dataPath;
        this.mapDataDao = new MapDataRepository(dataPath, false, "");
    }
    public MapNode loadMap() throws NoSuchItemException {
        LoaderService service = new LoaderService(dataPath);
        nodes = service.loadMapNodes();
        items = service.loadItems();
        statsMap  = service.loadStats();
        List<SubActionModel> subActions = service.loadSubAction(items);
        List<RequirementModel> requirements = service.loadRequirements(items);
        service.loadActions(subActions,nodes, requirements);
        return nodes.get(0);
    }

    public void restockMapItems(List<MapNodeEntity> dataSource) throws NoSuchItemException {
        for (MapNodeEntity nodeData:dataSource) {
            Optional<MapNode> first = nodes.stream().filter(s -> s.getId() == nodeData.getId()).findFirst();
            if(first.isEmpty()) {
                String name = nodes.getClass().getComponentType().getCanonicalName();
                throw new NoSuchItemException(nodeData.getId(), name);
            }
            HashMap<Integer, LocalItem> mapItems = first.get().getItems();
            for (AssignedItemEntity itemData :nodeData.getItemsHere())
            {
                LocalItem localItem = mapItems.get(itemData.getItemId());
                if(localItem == null) {
                    String name = nodes.getClass().getComponentType().getCanonicalName();
                    throw new NoSuchItemException(nodeData.getId(), name);
                }
                localItem.setValue(itemData.getAmount());
            }
        }
    }

    public void saveMap()
    {
        ArrayList<MapNodeEntity> mapNodeEntities = new ArrayList<>();
        for (MapNode node: nodes) {
            mapNodeEntities.add(ModelToEntityConverter.getNodeEntity(node));
        }
        mapDataDao.saveAll(mapNodeEntities);
    }

}
