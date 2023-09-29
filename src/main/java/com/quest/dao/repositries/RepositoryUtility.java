package com.quest.dao.repositries;

import com.quest.commons.interfaces.BiConsumerWException;
import com.quest.commons.interfaces.FunctionWException;
import com.quest.commons.interfaces.ReadableEnum;
import com.quest.dao.entities.AssignedItemEntity;
import com.quest.dao.entities.AssignedStatEntity;

import java.io.*;
import java.util.*;

public class  RepositoryUtility {

    public  static <T> void writeCollection(ObjectOutputStream outputStream, List<T> collection,
                                            BiConsumerWException<ObjectOutputStream,T> writer) throws Exception {
        outputStream.writeInt(collection.size());
        for (T entity :collection) {
            writer.accept(outputStream, entity);
        }
    }

    public static <K,V> void writeMap(ObjectOutputStream outputStream, Map<K,V> map, BiConsumerWException<ObjectOutputStream,K> keyWriter,
                                      BiConsumerWException<ObjectOutputStream,V> valueWriter) throws Exception {
        outputStream.writeInt(map.size());
        Set<K> keys = map.keySet();
        for (K key:keys) {
            V value = map.get(key);
            keyWriter.accept(outputStream, key);
            valueWriter.accept(outputStream, value);
        }
    }

    public static <K,V> Map<K,V> readMap(ObjectInputStream inputStream, FunctionWException<ObjectInputStream,K> keyReader,
                                         FunctionWException<ObjectInputStream,V> valueReader) throws Exception {
        int itemsAmount = inputStream.readInt();
        Map<K,V> map = new HashMap<>();
        while (itemsAmount>0)
        {
            map.put(keyReader.apply(inputStream),valueReader.apply(inputStream));
            itemsAmount--;
        }
        return map;
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
        outputStream.writeInt(entity.getMaxAmount());
    }

    public static AssignedStatEntity readStat(ObjectInputStream inputStream) throws IOException {
        int id = inputStream.readInt();
        int statId = inputStream.readInt();
        int value = inputStream.readInt();
        boolean critical = inputStream.readBoolean();
        int max = inputStream.readInt();
        AssignedStatEntity statEntity = new AssignedStatEntity(id, statId, value , critical);
        statEntity.setMaxStat(max);
        return statEntity;
    }

    public static AssignedItemEntity readAssignedItem(ObjectInputStream inputStream) throws IOException {
        int id = inputStream.readInt();
        int itemId = inputStream.readInt();
        int amount = inputStream.readInt();
        int max = inputStream.readInt();
        AssignedItemEntity entity = new AssignedItemEntity(id, itemId, amount);
        entity.setMaxAmount(max);
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

    public static void saveStat(ObjectOutputStream outputStream, AssignedStatEntity entity) throws IOException {
        outputStream.writeInt(entity.getId());
        outputStream.writeInt(entity.getItemId());
        outputStream.writeInt(entity.getAmount());
        outputStream.writeBoolean(entity.isCritical());
        outputStream.writeInt(entity.getMaxStat());
    }

    public static void writeEnum(ObjectOutputStream outputStream, ReadableEnum enumData) throws IOException {
        outputStream.writeUTF(enumData.getClass().getName());
        outputStream.writeInt(enumData.ordinal());
    }

    public static ReadableEnum readEnum(ObjectInputStream inputStream) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<? extends ReadableEnum> enumClass = (Class<? extends ReadableEnum>) Class.forName(inputStream.readLine());
        ReadableEnum readableEnum = enumClass.newInstance();
        return readableEnum.getValue(inputStream.readInt());
    }
}
