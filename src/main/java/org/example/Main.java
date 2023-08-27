package org.example;

import com.quest.services.models.ActionModel;
import com.quest.dao.entities.MapNodeEntity;
import com.quest.dao.repositries.MapDataRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        MapDataRepository mapDataRepository = new MapDataRepository("datarepository.properties");
        MapNodeEntity entity = new MapNodeEntity(1,"Start");
    }
}