package com.quest.commons.exceptions;

public class NoSuchItemException extends Exception{

    public NoSuchItemException(int id, String name)
    {
        super("There is no "+name+" with id "+id);
    }
}
