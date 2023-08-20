package com.quest.dao.repositries;

import com.quest.dao.interfaces.MapDataDao;
import com.quest.dao.entities.MapNodeEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MapDataRepository implements MapDataDao {


    private static final String PROP_KEY_MAP_PATH = "mappath";

    private Properties propertiesAccessPoint;


    public MapDataRepository(String propsFilepath)
    {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        propertiesAccessPoint = new Properties();
        try {
            propertiesAccessPoint.load(new FileInputStream(rootPath+""+propsFilepath));
        } catch (IOException e) {

        }
    }

    @Override
    public List<MapNodeEntity> getMap() {
        ArrayList<MapNodeEntity> mapList = new ArrayList<>();
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_MAP_PATH);
        try(FileInputStream fileInputStream = new FileInputStream(mapFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                Object data = inputStream.readObject();
                mapList.add((MapNodeEntity) data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return mapList;
    }

    @Override
    public boolean saveNode(MapNodeEntity entity) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_MAP_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath,true)))
        {
            outputStream.write(entity.getId());
            outputStream.writeChars(entity.getDescription());
            outputStream.writeBoolean(entity.isFinale());
            outputStream.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
