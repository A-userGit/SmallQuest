package com.quest.services.logic;

import com.quest.commons.exceptions.DataException;
import com.quest.commons.exceptions.NoSuchItemException;

public class GameService {

    private String dataPath;

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public void startGame( ) throws DataException {
        MapService mapService = new MapService(dataPath);
        try{
            mapService.loadMap();
        }catch (NoSuchItemException e){
            throw new DataException(e, this.getClass().getSimpleName());
        }
        UserService userService = new UserService(dataPath);
        userService.getDefaultUser()
    }

}
