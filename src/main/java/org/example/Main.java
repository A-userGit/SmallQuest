package org.example;

import com.quest.dao.models.ActionEntity;
import com.quest.dao.models.MapNodeEntity;
import com.quest.dao.repositries.MapDataRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        MapDataRepository mapDataRepository = new MapDataRepository("datarepository.properties");
        MapNodeEntity entity = new MapNodeEntity(1,"Start");
        entity.setFinale(false);
        entity.getActions().add(new ActionEntity(1,"Go Strait",1));
     //   mapDataRepository.saveNode(entity);
        List<MapNodeEntity> map = mapDataRepository.getMap();
        MapNodeEntity entity1 = map.get(0);
        ActionEntity actionEntity = entity1.getActions().get(0);
        System.out.println(actionEntity.getDescription());
        System.out.println(entity1.getDescription());
        System.out.println(map.size());
    }
}