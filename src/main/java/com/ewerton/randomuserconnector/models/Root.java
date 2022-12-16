package com.ewerton.randomuserconnector.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

@JsonRootName("")
public class Root implements Serializable {

    @JsonAlias("results")
    private List<Result> results;

    public Root() {
    }

    public Root(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
