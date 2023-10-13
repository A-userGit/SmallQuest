package com.quest.dao.repositries;

import com.quest.commons.interfaces.FunctionWException;
import com.quest.commons.models.ContainerItemModel;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.commons.types.ItemPlace;
import com.quest.commons.types.ItemType;
import com.quest.dao.entities.ContainerIdElement;
import com.quest.dao.interfaces.ItemsDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemsRepository implements ItemsDao {

    private static final String PROP_KEY_ITEMS_PATH = "itempath";
    private static final String PROP_KEY_STATS_PATH = "statpath";

    private static final String PROP_KEY_CONTAINER_PATH = "containerpath";

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
        FunctionWException<ObjectInputStream, ItemModel> readerFunction = null;
        switch (itemDaoType)
        {
            case ITEM: {
                itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_ITEMS_PATH);
                readerFunction = this::readEntity;
            }
            break;
            case STAT: {
                itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_STATS_PATH);
                readerFunction = this::readEntity;
            }
            break;
            case CONTAINER: {
                itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_CONTAINER_PATH);
                readerFunction = this::readContainerEntity;
            }
        }
        try(FileInputStream fileInputStream = new FileInputStream(itemsFilePath);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream))
        {
            while (fileInputStream.available()>0)
            {
                itemsList.add(readerFunction.apply(inputStream));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
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
    public boolean saveContainerEntity(ContainerItemModel<ContainerIdElement> itemModel) {
        String itemsFilePath = propertiesAccessPoint.getProperty(PROP_KEY_CONTAINER_PATH);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(itemsFilePath,true)))
        {
            writeContainerEntity(outputStream, itemModel);
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
        outputStream.writeInt(entity.getType().ordinal());
    }

    private void writeContainerEntity(ObjectOutputStream outputStream, ContainerItemModel<ContainerIdElement> entity) throws Exception {
        outputStream.writeInt(entity.getId());
        outputStream.writeChars(entity.getDescription());
        outputStream.writeBoolean(entity.isVisibleIfZero());
        outputStream.writeBoolean(entity.isInfinite());
        outputStream.writeInt(entity.getType().ordinal());
        List<ContainerIdElement> elements = entity.getElements();
        RepositoryUtility.writeCollection(outputStream, elements, this::writeContainerElement);
    }

    private void writeContainerElement(ObjectOutputStream outputStream,ContainerIdElement element) throws IOException {
        outputStream.writeInt(element.getItemId());
        outputStream.writeInt(element.getQuantity());
    }

    private ItemModel readEntity(ObjectInputStream inputStream) throws IOException {
        int id = inputStream.readInt();
        String description = inputStream.readLine();
        boolean visibleIfZero = inputStream.readBoolean();
        boolean infinite = inputStream.readBoolean();
        ItemModel itemModel = new ItemModel(id, description, visibleIfZero, infinite);
        itemModel.setType(ItemType.values()[inputStream.readInt()]);
        return itemModel;
    }

    private ContainerIdElement readContainerElement(ObjectInputStream inputStream) throws IOException {
        ContainerIdElement containerIdElement = new ContainerIdElement(inputStream.readInt());
        containerIdElement.setQuantity(inputStream.readInt());
        return containerIdElement;
    }

    private ContainerItemModel<ContainerIdElement> readContainerEntity(ObjectInputStream inputStream) throws Exception {
        ItemModel itemModel = readEntity(inputStream);
        ContainerItemModel<ContainerIdElement> entity = new ContainerItemModel<>(itemModel.getId(),itemModel.getDescription(),
                itemModel.isVisibleIfZero(), itemModel.isInfinite());
        List<ContainerIdElement> containerItems = RepositoryUtility.readCollection(inputStream, this::readContainerElement);
        entity.setElements(containerItems);
        return entity;
    }

    @Override
    public void setItemDaoType(ItemDaoType itemDaoType) {
        this.itemDaoType = itemDaoType;
    }
}
