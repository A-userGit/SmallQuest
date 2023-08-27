package com.quest.dao.repositries;

import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.dao.interfaces.ItemsDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemsRepository implements ItemsDao {

    private static final String PROP_KEY_ITEMS_PATH = "itempath";
    private static final String PROP_KEY_STATS_PATH = "statpath";

    private Properties propertiesAccessPoint;

    private ItemDaoType itemDaoType;

    public ItemsRepository(String propsFilepath)
    {
        try {
            propertiesAccessPoint = RepositoryUtility.loadProperties(propsFilepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemModel> getList() {
        ArrayList<ItemModel> itemsList = new ArrayList<>();
        String itemsFilePath = "";
        if(itemDaoType == ItemDaoType.ITEM)
            itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ITEMS_PATH);
        else
            itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_STATS_PATH);
        try(FileInputStream fileInputStream = new FileInputStream(itemsFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                int id = inputStream.readInt();
                String description = inputStream.readLine();
                boolean visibleIfZero = inputStream.readBoolean();
                boolean infinite = inputStream.readBoolean();
                ItemModel itemModel = new ItemModel(id, description, visibleIfZero, infinite);
                itemsList.add(itemModel);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemsList;
    }

    @Override
    public boolean saveEntity(ItemModel itemModel) {
        String itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ITEMS_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(itemsFilePath,true)))
        {
            writeEntity(outputStream, itemModel);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean saveAll(List<ItemModel> list) {
        String itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ITEMS_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(itemsFilePath)))
        {
            for (ItemModel entity:list) {
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

    private void writeEntity(ObjectOutputStream outputStream, ItemModel entity) throws IOException {
        outputStream.write(entity.getId());
        outputStream.writeChars(entity.getDescription());
        outputStream.writeBoolean(entity.isVisibleIfZero());
        outputStream.writeBoolean(entity.isInfinite());
    }

    @Override
    public void setItemDaoType(ItemDaoType itemDaoType) {
        this.itemDaoType = itemDaoType;
    }
}
