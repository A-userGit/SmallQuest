package com.quest.dao.interfaces;

import com.quest.dao.entities.UserEntity;

public interface UserDao {
    UserEntity loadSave(String actionsFilePath);

    boolean saveEntity(UserEntity entity, String mapFilePath);

    public UserEntity getDefaultUser();
}
