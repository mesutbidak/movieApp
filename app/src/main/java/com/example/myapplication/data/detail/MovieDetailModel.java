package com.example.myapplication.data.detail;
import com.example.myapplication.data.detail.genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailModel {
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("genres")
    private List<genre> genres = null;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("release_date")
    private String releaseDate;

    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<genre> getGenres() {
        return genres;
    }
    public void setGenres(List<genre> genres) { this.genres = genres; }

    public double getVoteAverage(){return voteAverage;}
    public void setVoteAverage(double voteAverage){this.voteAverage=voteAverage;}

    public String getReleaseDate(){return releaseDate;}
    public void setReleaseDate(String releaseDate){this.releaseDate=releaseDate;}
}
