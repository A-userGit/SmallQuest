package com.quest.commons.exceptions;

public class DataException extends Exception{

    String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public DataException(Exception rootCause, String place)
    {
        super(rootCause);
        this.place = place;
    }
}
