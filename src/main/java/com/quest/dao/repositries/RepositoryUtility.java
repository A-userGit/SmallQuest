package com.quest.dao.repositries;

import com.quest.commons.interfaces.BiConsumerWException;
import com.quest.commons.interfaces.FunctionWException;
import com.quest.dao.entities.AssignedItemEntity;
import com.quest.dao.entities.AssignedLocalStatEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class  RepositoryUtility {

    public  static <T> void writeCollection(ObjectOutputStream outputStream, List<T> collection,
                                            BiConsumerWException<ObjectOutputStream,T> writer) throws Exception {
        outputStream.writeInt(collection.size());
        for (T entity :collection) {
            writer.accept(outputStream, entity);
        }
    }

    public  static <T> List<T> readCollection(ObjectInputStream inputStream,
                                            FunctionWException<ObjectInputStream,T> reader) throws Exception {
        List<T> collection = new ArrayList<>();
        int itemsAmount = inputStream.readInt();
        while (itemsAmount>0)
        {
            collection.add(reader.apply(inputStream));
            itemsAmount--;
        }
        return collection;
    }

    public static void saveAssignedItem(ObjectOutputStream outputStream, AssignedItemEntity entity) throws IOException {
        outputStream.writeInt(entity.getId());
        outputStream.writeInt(entity.getItemId());
        outputStream.writeInt(entity.getAmount());
    }

    public static AssignedLocalStatEntity readStat(ObjectInputStream inputStream) throws IOException {
        int id = inputStream.readInt();
        int statId = inputStream.readInt();
        int value = inputStream.readInt();
        boolean critical = inputStream.readBoolean();
        AssignedLocalStatEntity statEntity = new AssignedLocalStatEntity(id, statId, value , critical);
        return statEntity;
    }

    public static AssignedItemEntity readAssignedItem(ObjectInputStream inputStream) throws IOException {
        int id = inputStream.readInt();
        int itemId = inputStream.readInt();
        int amount = inputStream.readInt();
        AssignedItemEntity entity = new AssignedItemEntity(id, itemId, amount);
        return entity;
    }

    public static void writeId(ObjectOutputStream outputStream, int id) throws IOException {
        outputStream.writeInt(id);
    }

    public static Properties loadProperties(String propsFilepath) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Properties propertiesAccessPoint = new Properties();
        propertiesAccessPoint.load(new FileInputStream(rootPath+""+propsFilepath));
        return propertiesAccessPoint;
    }

    public static int readId(ObjectInputStream inputStream) throws IOException {
        return inputStream.readInt();
    }

    public static void saveStat(ObjectOutputStream outputStream, AssignedLocalStatEntity entity) throws IOException {
        outputStream.writeInt(entity.getId());
        outputStream.writeInt(entity.getItemId());
        outputStream.writeInt(entity.getAmount());
        outputStream.writeBoolean(entity.isCritical());
    }
}
