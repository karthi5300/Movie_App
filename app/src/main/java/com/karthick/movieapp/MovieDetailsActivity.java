package com.karthick.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.karthick.movieapp.data.CurrentMoviesData;
import com.karthick.movieapp.data.MovieDetails;
import com.karthick.movieapp.data.TheMovieDBApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView mPosterPath;
    private TextView mTitle, mGenreValue, mStatusValue, mRatingValue, mOverviewValue;
    private String movieId;
    private String posterUrl;
    private String API_KEY = "55957fcf3ba81b137f8fc01ac5a31fb5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        setTitle("Movie Details");

        mTitle = findViewById(R.id.title);
        mPosterPath = findViewById(R.id.poster);
        mGenreValue = findViewById(R.id.genre_value);
        mStatusValue = findViewById(R.id.status_value);
        mRatingValue = findViewById(R.id.rating_value);
        mOverviewValue = findViewById(R.id.overview_value);

        Intent receivedIntent = getIntent();
        movieId = receivedIntent.getStringExtra("id");
        posterUrl = receivedIntent.getStringExtra("poster_path");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBApi t = retrofit.create(TheMovieDBApi.class);

        Call<MovieDetails> call = t.getMovieDetails(movieId, API_KEY);

        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                MovieDetails movieDetails = response.body();

                ArrayList<String> genres = new ArrayList<>();

                for (int i = 0; i < movieDetails.getGenres().length; i++) {
                    genres.add(i, movieDetails.getGenres()[i].getName());
                }

                //CONVERT genres ARRAYLIST TO STRING AND SET TO GENRE
                StringBuilder sb = new StringBuilder();
                for (String s : genres) {
                    sb.append(s);
                    sb.append("  ");
                }
                mGenreValue.setText(sb.toString());

                //SET IMAGE
                Picasso.get().load(posterUrl).resize(300, 600).centerCrop().into(mPosterPath);

                //SET TITLE
                mTitle.setText(movieDetails.getTitle());

                //SET STATUS
                mStatusValue.setText(movieDetails.getStatus());

                //SET RATINGS
                mRatingValue.setText(movieDetails.getRating() + "★");

                //SET OVERVIEW
                mOverviewValue.setText(movieDetails.getOverview());
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
