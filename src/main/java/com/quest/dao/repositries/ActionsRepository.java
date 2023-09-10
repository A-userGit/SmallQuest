package com.quest.dao.repositries;

import com.quest.commons.types.ActionRole;
import com.quest.commons.types.ActionType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.interfaces.ActionsDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ActionsRepository implements ActionsDao {

    private static final String PROP_KEY_ACTIONS_PATH = "actpath";

    private Properties propertiesAccessPoint;


    public ActionsRepository(String propsFilepath)
    {
        try {
            propertiesAccessPoint = RepositoryUtility.loadProperties(propsFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ActionEntity> getList() {
        ArrayList<ActionEntity> actionsList = new ArrayList<>();
        String actionsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ACTIONS_PATH);
        ActionType[] types = ActionType.values();
        try(FileInputStream fileInputStream = new FileInputStream(actionsFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                int id = inputStream.readInt();
                String description = inputStream.readLine();
                int type = inputStream.readInt();
                ActionEntity actionsEntity = new ActionEntity(id, description, types[type]);
                actionsEntity.setActionRole(ActionRole.values()[inputStream.readInt()]);
                actionsEntity.setTurnsActive(inputStream.readInt());
                actionsEntity.setSubActions(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readId));
                actionsEntity.setRequirements(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readId));
                actionsEntity.setNodesToGo(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readId));
                actionsEntity.setActionsToExecute(RepositoryUtility.readCollection(inputStream, RepositoryUtility::readId));
                actionsList.add(actionsEntity);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return actionsList;
    }

    @Override
    public boolean saveEntity(ActionEntity entity) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ACTIONS_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath)))
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
    public boolean saveAll(List<ActionEntity> list)
    {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ACTIONS_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath,true)))
        {
            for (ActionEntity entity:list) {
                writeEntity(outputStream, entity);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void writeEntity(ObjectOutputStream outputStream, ActionEntity entity) throws IOException {
        outputStream.write(entity.getId());
        outputStream.writeChars(entity.getDescription());
        outputStream.writeInt(entity.getActionType().ordinal());
        outputStream.writeInt(entity.getActionRole().ordinal());
        outputStream.writeInt(entity.getTurnsActive());
        try {
            RepositoryUtility.writeCollection(outputStream,entity.getSubActions(), RepositoryUtility::writeId);
            RepositoryUtility.writeCollection(outputStream,entity.getRequirements(), RepositoryUtility::writeId);
            RepositoryUtility.writeCollection(outputStream,entity.getNodesToGo(), RepositoryUtility::writeId);
            RepositoryUtility.writeCollection(outputStream,entity.getActionsToExecute(), RepositoryUtility::writeId);
        } catch (Exception e) {
            throw (IOException) e;
        }

    }

}
