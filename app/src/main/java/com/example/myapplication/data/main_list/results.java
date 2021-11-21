package com.example.myapplication.data.main_list;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class results {
    @SerializedName("results")
    private List<MovieModel> results=null;

    public List<MovieModel> getResults() {
        return results;
    }

    public void setResults(List<MovieModel> results) {
        this.results = results;
    }
}
