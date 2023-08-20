package com.quest.dao.interfaces;

import com.quest.dao.entities.MapNodeEntity;

import java.util.List;

public interface MapDataDao {
    public List<MapNodeEntity> getMap();

    public boolean saveNode(MapNodeEntity entity);

}
