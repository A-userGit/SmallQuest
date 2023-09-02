package com.quest.dao.repositries;

import com.quest.dao.entities.UserEntity;
import com.quest.dao.interfaces.UserDao;

import java.io.*;
import java.util.Properties;

public class UserRepository implements UserDao {

    private static final String PROP_KEY_DEFAULT_USER = "defuserpath";

    private String propsFilepath;

    public UserRepository(String propsFilepath) {
        this.propsFilepath = propsFilepath;
    }

    @Override
    public UserEntity loadSave(String filePath) {
        UserEntity entity = new UserEntity(0,"",0);
        try(FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            int id = inputStream.readInt();
            String name = inputStream.readLine();
            int nodeId = inputStream.readInt();
            entity.setId(id);
            entity.setDescription(name);
            entity.setNodeId(nodeId);
            entity.setStats(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readStat));
            entity.setInventoryItems(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readAssignedItem));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public boolean saveEntity(UserEntity entity, String mapFilePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath)))
        {
            outputStream.writeInt(entity.getId());
            outputStream.writeChars(entity.getDescription());
            outputStream.writeInt(entity.getNodeId());
            RepositoryUtility.writeCollection(outputStream, entity.getStats(), RepositoryUtility::saveStat);
            RepositoryUtility.writeCollection(outputStream, entity.getInventoryItems(), RepositoryUtility::saveAssignedItem);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public UserEntity getDefaultUser() {

        Properties propertiesAccessPoint;
        try {
            propertiesAccessPoint = RepositoryUtility.loadProperties(propsFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String property = propertiesAccessPoint.getProperty(PROP_KEY_DEFAULT_USER);
        return loadSave(property);
    }
}
