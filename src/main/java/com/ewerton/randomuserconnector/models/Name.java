package com.ewerton.randomuserconnector.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Name implements Serializable {

    private static final String EMPTY_SPACE = " ";

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @JsonIgnore
    public String getFullName() {
        return getTitle() +
                EMPTY_SPACE +
                getFirst() +
                EMPTY_SPACE +
                getLast();
    }

}
