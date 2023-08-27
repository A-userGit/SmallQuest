package com.quest.dao.repositries;

import com.quest.commons.models.ItemModel;
import com.quest.dao.entities.AssignedItemEntity;
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
        try {
            propertiesAccessPoint = RepositoryUtility.loadProperties(propsFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MapNodeEntity> getList() {
        ArrayList<MapNodeEntity> mapList = new ArrayList<>();
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_MAP_PATH);
        try(FileInputStream fileInputStream = new FileInputStream(mapFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                int id = inputStream.readInt();
                String description = inputStream.readLine();
                MapNodeEntity nodeEntity = new MapNodeEntity(id, description);
                nodeEntity.setFinale(inputStream.readBoolean());
                nodeEntity.setActions(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readId));
                nodeEntity.setItemsHere(RepositoryUtility.readCollection(inputStream,RepositoryUtility::readAssignedItem));
                nodeEntity.setEnvironmentActions(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readId));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mapList;
    }

    @Override
    public boolean saveEntity(MapNodeEntity entity) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_MAP_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath,true)))
        {
            writeEntity(outputStream, entity);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean saveAll(List<MapNodeEntity> list) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_MAP_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath)))
        {
            for (MapNodeEntity entity:list) {
                writeEntity(outputStream, entity);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void writeEntity(ObjectOutputStream outputStream, MapNodeEntity entity) throws IOException {
        outputStream.write(entity.getId());
        outputStream.writeChars(entity.getDescription());
        outputStream.writeBoolean(entity.isFinale());
        try {
            RepositoryUtility.writeCollection(outputStream,entity.getActions(),
                    RepositoryUtility::writeId);
            RepositoryUtility.writeCollection(outputStream, entity.getItemsHere(),
                    RepositoryUtility::saveAssignedItem);
            RepositoryUtility.writeCollection(outputStream,entity.getEnvironmentActions(),
                    RepositoryUtility::writeId);
        } catch (Exception e) {
            throw (IOException) e;
        }

    }
}
