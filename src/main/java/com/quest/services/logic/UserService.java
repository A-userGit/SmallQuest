package com.quest.services.logic;

import com.quest.commons.exceptions.NoSuchItemException;
import com.quest.commons.models.ItemModel;
import com.quest.dao.entities.AssignedItemEntity;
import com.quest.dao.entities.AssignedLocalStatEntity;
import com.quest.dao.entities.UserEntity;
import com.quest.dao.interfaces.UserDao;
import com.quest.dao.repositries.UserRepository;
import com.quest.services.models.LocalItem;
import com.quest.services.models.LocalStat;
import com.quest.services.models.MapNode;
import com.quest.services.models.UserModel;

import java.util.HashMap;
import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService()
    {
        userDao = new UserRepository();
    }
    public void getUser(String fileName, List<MapNode> mapNodes, HashMap<Integer, ItemModel> itemsMap, HashMap<Integer,ItemModel> stats) throws NoSuchItemException {
        UserEntity userEntity = userDao.loadSave(fileName);
        UserModel userModel = EntitiesToModelsConverter.getUserModel(userEntity);
        List<AssignedItemEntity> inventoryItems = userEntity.getInventoryItems();
        HashMap<Integer, LocalItem> userItems = EntitiesToModelsConverter.getMapFromIds(inventoryItems,itemsMap, EntitiesToModelsConverter::getLocalItem);
        userModel.setInventory(userItems);
        List<AssignedLocalStatEntity> assignedStats = userEntity.getStats();
        HashMap<Integer, LocalStat> userStats = EntitiesToModelsConverter.getMapFromIds(assignedStats, stats, EntitiesToModelsConverter::getLocalStat);
        userModel.setPosition();
        userModel.setStats(userStats);
    }
}
