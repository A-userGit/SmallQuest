package com.quest.dao.repositries;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;
import com.quest.dao.entities.ActionEntity;
import com.quest.dao.entities.RequirementEntity;
import com.quest.dao.entities.SubActionEntity;
import com.quest.dao.interfaces.SubActionsDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SubActionRepository implements SubActionsDao {
    private static final String PROP_KEY_SACTIONS_PATH = "subactpath";

    private Properties propertiesAccessPoint;


    public SubActionRepository(String propsFilepath)
    {
        try {
            propertiesAccessPoint = RepositoryUtility.loadProperties(propsFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SubActionEntity> getList() {
        ArrayList<SubActionEntity> actionsList = new ArrayList<>();
        String actionsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_SACTIONS_PATH);
        try(FileInputStream fileInputStream = new FileInputStream(actionsFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                int id = inputStream.readInt();
                boolean random = inputStream.readBoolean();
                int itemId = inputStream.readInt();
                int startVal = inputStream.readInt();
                int topVal = inputStream.readInt();
                SubActionEntity subActionsEntity = new SubActionEntity(id, random, itemId, startVal, topVal);
                subActionsEntity.setItemType(ItemType.values()[inputStream.readInt()]);
                subActionsEntity.setSubActionType(ItemActionType.values()[inputStream.readInt()]);
                subActionsEntity.setGenerateItemId(inputStream.readInt());
                actionsList.add(subActionsEntity);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return actionsList;
    }

    @Override
    public boolean saveEntity(SubActionEntity entity) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_SACTIONS_PATH);
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
    public boolean saveAll(List<SubActionEntity> list) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_SACTIONS_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath)))
        {
            for (SubActionEntity entity:list) {
                writeEntity(outputStream, entity);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private void writeEntity(ObjectOutputStream outputStream, SubActionEntity entity) throws IOException {
        outputStream.write(entity.getId());
        outputStream.writeBoolean(entity.isRandom());
        outputStream.writeInt(entity.getItemId());
        outputStream.writeInt(entity.getStartAmount());
        outputStream.writeInt(entity.getRandomTopBorder());
        outputStream.writeInt(entity.getItemType().ordinal());
        outputStream.writeInt(entity.getSubActionType().ordinal());
        outputStream.writeInt(entity.getGenerateItemId());
    }
}
