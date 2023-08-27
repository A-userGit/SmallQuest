package com.quest.dao.repositries;

import com.quest.commons.types.ItemActionType;
import com.quest.commons.types.ItemType;
import com.quest.commons.types.RestrictionType;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.entities.RequirementEntity;
import com.quest.dao.interfaces.RequirementsDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReqirementsRepository implements RequirementsDao {

    private static final String PROP_KEY_REQ_PATH = "reqpath";

    private Properties propertiesAccessPoint;


    public ReqirementsRepository(String propsFilepath)
    {
        try {
            propertiesAccessPoint = RepositoryUtility.loadProperties(propsFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RequirementEntity> getList() {
        ArrayList<RequirementEntity> requirementEntities = new ArrayList<>();
        String requirementsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_REQ_PATH);
        try(FileInputStream fileInputStream = new FileInputStream(requirementsFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                int id = inputStream.readInt();
                String description = inputStream.readLine();
                int value = inputStream.readInt();
                int itemId = inputStream.readInt();
                boolean blocker = inputStream.readBoolean();
                RequirementEntity requirementEntity = new RequirementEntity(id, description, value, blocker);
                requirementEntity.setItemId(itemId);
                requirementEntity.setItemType(ItemType.values()[inputStream.readInt()]);
                requirementEntity.setRestrictionType(RestrictionType.values()[inputStream.readInt()]);
                requirementEntity.setActionType(ItemActionType.values()[inputStream.readInt()]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return requirementEntities;
    }

    @Override
    public boolean saveEntity(RequirementEntity entity) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_REQ_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath,true)))
        {

            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean saveAll(List<RequirementEntity> list) {
        String mapFilePath = propertiesAccessPoint.getProperty(PROP_KEY_REQ_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(mapFilePath)))
        {
            for (RequirementEntity entity:list) {
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

    private void writeEntity(ObjectOutputStream outputStream, RequirementEntity entity) throws IOException {
        outputStream.write(entity.getId());
        outputStream.writeChars(entity.getDescription());
        outputStream.writeInt(entity.getValue());
        outputStream.writeInt(entity.getItemId());
        outputStream.writeBoolean(entity.isBlocker());
        outputStream.writeInt(entity.getItemType().ordinal());
        outputStream.writeInt(entity.getRestrictionType().ordinal());
        outputStream.writeInt(entity.getActionType().ordinal());
    }
}
