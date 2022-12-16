package com.ewerton.randomuserconnector.models;

import java.io.Serializable;

public class Result implements Serializable {

    private Name name;

    private String gender;

    private String email;

    private Location location;

    public Result() {
    }

    public Result(Name name, String gender, String email, Location location) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.location = location;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name=" + name +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", location=" + location +
                '}';
    }
}
