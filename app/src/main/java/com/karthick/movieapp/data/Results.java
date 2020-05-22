package com.karthick.movieapp.data;

import com.google.gson.annotations.SerializedName;

public class Results {

    private String poster_path, id;

    @SerializedName("vote_average")
    private String rating;

    public String getPoster_path() {
        return poster_path;
    }

    public String getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

}
