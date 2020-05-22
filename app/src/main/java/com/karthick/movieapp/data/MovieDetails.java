package com.karthick.movieapp.data;

import com.google.gson.annotations.SerializedName;

public class MovieDetails {

    private String title;

    @SerializedName("vote_average")
    private String rating;

    private Genres[] genres;
    private String status;
    private String poster_path;
    private String overview;

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public Genres[] getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }
}
