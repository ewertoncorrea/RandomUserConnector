package com.ewerton.randomuserconnector.models;

import java.io.Serializable;

public class Street implements Serializable {

    private String number;

    private String name;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}