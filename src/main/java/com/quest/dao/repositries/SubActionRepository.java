package com.quest.dao.repositries;

import com.quest.commons.interfaces.ReadableEnum;
import com.quest.commons.models.subaction.fieldconnectors.ActionItemFieldConnector;
import com.quest.commons.types.ActionDataTypes;
import com.quest.commons.types.ActionFunctionType;
import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemPlace;
import com.quest.dao.entities.SubActionEntity;
import com.quest.dao.interfaces.SubActionsDao;
import com.quest.dao.repositries.subaction.FieldConnectorsRepoProvider;
import com.quest.dao.repositries.subaction.subactdata.ActionDataProvider;
import com.quest.dao.repositries.subaction.ActionDataSourceInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

                actionsList.add(readEntity(inputStream));
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private SubActionEntity readEntity(ObjectInputStream inputStream) throws Exception {
        SubActionEntity subActionsEntity = new SubActionEntity(inputStream.readInt(), inputStream.readInt());
        subActionsEntity.setItemPlace(ItemPlace.values()[inputStream.readInt()]);
        subActionsEntity.setItemActionType(ItemActionType.values()[inputStream.readInt()]);
        subActionsEntity.setActionDataType(ActionDataTypes.values()[inputStream.readInt()]);
        subActionsEntity.setActionFunctionType(ActionFunctionType.values()[inputStream.readInt()]);
        ActionDataSourceInterface provider = ActionDataProvider.getProvider(subActionsEntity.getActionDataType());
        subActionsEntity.setChangeData(provider.readData(inputStream));
        Map<ActionItemFieldConnector, ReadableEnum> readableEnumMap = RepositoryUtility.readMap(inputStream,
                FieldConnectorsRepoProvider::getConnector, RepositoryUtility::readEnum);
        subActionsEntity.setSourceConsumerPairs(readableEnumMap);
        return subActionsEntity;
    }

    private void writeEntity(ObjectOutputStream outputStream, SubActionEntity entity) throws Exception {
        outputStream.write(entity.getId());
        outputStream.writeInt(entity.getItemId());
        outputStream.writeInt(entity.getItemPlace().ordinal());
        outputStream.writeInt(entity.getItemActionType().ordinal());
        outputStream.writeInt(entity.getActionDataType().ordinal());
        outputStream.writeInt(entity.getActionFunctionType().ordinal());
        ActionDataSourceInterface provider = ActionDataProvider.getProvider(entity.getActionDataType());
        provider.writeData(outputStream, entity.getChangeData());
        RepositoryUtility.writeMap(outputStream, entity.getSourceConsumerPairs(), FieldConnectorsRepoProvider::writeConnector,
                RepositoryUtility::writeEnum);
    }
}
