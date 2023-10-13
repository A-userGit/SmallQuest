package com.quest.dao.repositries.subaction.subactdata;

import com.quest.commons.models.subaction.subactdata.ActionDataInterface;
import com.quest.commons.models.subaction.subactdata.SimpleIntActionData;
import com.quest.dao.repositries.subaction.ActionDataSourceInterface;
import com.quest.services.logic.functions.argtypes.SimpleArithmetics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SimpleValActionProvider implements ActionDataSourceInterface {
    @Override
    public ActionDataInterface readData(ObjectInputStream reader) throws IOException {
        SimpleIntActionData simpleIntActionData = new SimpleIntActionData(reader.readInt());
        simpleIntActionData.setAction(SimpleArithmetics.values()[reader.readInt()]);
        return simpleIntActionData;
    }

    @Override
    public void writeData(ObjectOutputStream writer, ActionDataInterface data) throws IOException {
        SimpleIntActionData intActionData = (SimpleIntActionData)data;
        writer.writeInt(intActionData.getChangeValue());
        writer.writeInt(intActionData.getAction().ordinal());
    }
}
