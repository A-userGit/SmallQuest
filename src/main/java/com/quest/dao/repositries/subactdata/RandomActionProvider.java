package com.quest.dao.repositries.subactdata;

import com.quest.commons.models.subactdata.ActionDataInterface;
import com.quest.commons.models.subactdata.RandomActionData;

import java.io.*;

public class RandomActionProvider implements ActionDataSourceInterface {
    @Override
    public ActionDataInterface readData(ObjectInputStream reader) throws IOException {
        int topBorder = reader.readInt();
        int floor = reader.readInt();
        RandomActionData actionData = new RandomActionData(topBorder, floor);
        return actionData;
    }

    @Override
    public void writeData(ObjectOutputStream writer, ActionDataInterface data) throws IOException {
        RandomActionData randomData = (RandomActionData)data;
        writer.writeInt(randomData.getTopBorder());
        writer.writeInt(randomData.getFloorBorder());
    }
}
