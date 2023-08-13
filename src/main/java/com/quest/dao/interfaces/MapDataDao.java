package com.quest.dao.interfaces;

import com.quest.dao.models.MapNodeEntity;

import java.util.List;

public interface MapDataDao {
    public List<MapNodeEntity> getMap();

    public boolean saveNode(MapNodeEntity entity);

}
