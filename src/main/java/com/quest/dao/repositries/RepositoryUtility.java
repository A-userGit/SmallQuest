package com.quest.dao.repositries;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryUtility {
    public  static void writeIdCollection(ObjectOutputStream outputStream, List<Integer> idCollection) throws IOException {
        outputStream.writeInt(idCollection.size());
        for (int id:idCollection) {
            outputStream.writeInt(id);
        }
    }

    public  static List<Integer> readIdCollection(ObjectInputStream inputStream) throws IOException {
        List<Integer> collection = new ArrayList<>();
        int itemsAmount = inputStream.readInt();
        while (itemsAmount>0)
        {
            collection.add(inputStream.readInt());
            itemsAmount--;
        }
        return collection;
    }

    public static Properties loadProperties(String propsFilepath) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Properties propertiesAccessPoint = new Properties();
        propertiesAccessPoint.load(new FileInputStream(rootPath+""+propsFilepath));
        return propertiesAccessPoint;
    }
}
