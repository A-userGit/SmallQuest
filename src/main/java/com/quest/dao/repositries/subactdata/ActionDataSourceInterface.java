package com.quest.dao.repositries.subactdata;

import com.quest.commons.models.subactdata.ActionDataInterface;

import java.io.*;

public interface ActionDataSourceInterface {

    public ActionDataInterface readData(ObjectInputStream reader) throws IOException;

    public void writeData(ObjectOutputStream writer, ActionDataInterface data) throws IOException;

}
