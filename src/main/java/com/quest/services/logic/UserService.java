package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.ItemModel;
import com.quest.dao.entities.AssignedItemEntity;
import com.quest.dao.entities.AssignedStatEntity;
import com.quest.dao.entities.UserEntity;
import com.quest.dao.interfaces.UserDao;
import com.quest.dao.repositries.UserRepository;
import com.quest.services.models.LocalItem;
import com.quest.services.models.LocalStat;
import com.quest.services.models.MapNode;
import com.quest.services.models.UserModel;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserService {

    private UserDao userDao;

    public UserService(String propsFilepath)
    {
        userDao = new UserRepository(propsFilepath);
    }
    public UserModel getUser(UserEntity userEntity, List<MapNode> mapNodes, HashMap<Integer, ItemModel> itemsMap, HashMap<Integer,ItemModel> stats) throws NoSuchItemException {
        UserModel userModel = EntitiesToModelsConverter.getUserModel(userEntity);
        List<AssignedItemEntity> inventoryItems = userEntity.getInventoryItems();
        HashMap<Integer, LocalItem> userItems = EntitiesToModelsConverter.getMapFromIds(inventoryItems,itemsMap, EntitiesToModelsConverter::getLocalItem);
        userModel.setInventory(userItems);
        List<AssignedStatEntity> assignedStats = userEntity.getStats();
        HashMap<Integer, LocalStat> userStats = EntitiesToModelsConverter.getMapFromIds(assignedStats, stats, EntitiesToModelsConverter::getLocalStat);
        Optional<MapNode> first = mapNodes.stream().filter(s -> s.getId() == userEntity.getNodeId()).findFirst();
        if(first.isEmpty()) {
            String name = mapNodes.getClass().getComponentType().getCanonicalName();
            throw new NoSuchItemException(userEntity.getNodeId(), name);
        }
        userModel.setPosition(first.get());
        userModel.setStats(userStats);
        return userModel;
    }

    public void saveUser(UserModel userModel,String filename)
    {
        UserEntity userEntity = ModelToEntityConverter.getUserEntity(userModel);
        userDao.saveEntity(userEntity, filename);
    }

    public UserModel loadUser(String filename, List<MapNode> mapNodes, HashMap<Integer, ItemModel> itemsMap, HashMap<Integer,ItemModel> stats) throws NoSuchItemException {
        UserEntity user = userDao.loadSave(filename);
        return getUser(user, mapNodes, itemsMap, stats);
    }

    public UserModel getDefaultUser(List<MapNode> mapNodes, HashMap<Integer, ItemModel> itemsMap, HashMap<Integer,ItemModel> stats) throws NoSuchItemException {
        UserEntity defaultUser = userDao.getDefaultUser();

        UserModel user = getUser(defaultUser, mapNodes, itemsMap, stats);
        return user;
    }
}
