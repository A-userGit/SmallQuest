package com.quest.dao.repositries;

import com.quest.dao.interfaces.MapDataDao;
import com.quest.dao.models.MapNodeEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MapDataRepository implements MapDataDao {

    private Properties propertiesAccessPoint;

    MapDataRepository()
    {
        propertiesAccessPoint = new Properties();
        try {
            propertiesAccessPoint.load(new FileInputStream(new File("datarepository.properties")));
        } catch (IOException e) {

        }
    }

    @Override
    public List<MapNodeEntity> getMap() {

        return null;
    }

    @Override
    public boolean saveNode(MapNodeEntity entity) {

    }
}
