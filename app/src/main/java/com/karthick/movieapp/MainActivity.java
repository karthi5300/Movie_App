package com.karthick.movieapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.karthick.movieapp.data.CurrentMoviesData;
import com.karthick.movieapp.data.TheMovieDBApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MOVIE";
    private String API_KEY = "55957fcf3ba81b137f8fc01ac5a31fb5";
    private String LANGUAGE = "en-US";
    private String USER = "undefined";
    private String POSTER_PATH = "https://image.tmdb.org/t/p/w780";
    private Context mContext = this;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Now Playing");

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBApi t = retrofit.create(TheMovieDBApi.class);

        Call<CurrentMoviesData> call = t.getCurrentMovies(API_KEY, LANGUAGE, USER);

        call.enqueue(new Callback<CurrentMoviesData>() {
            @Override
            public void onResponse(Call<CurrentMoviesData> call, Response<CurrentMoviesData> response) {

                if (!response.isSuccessful()) {
                    return;
                }


                CurrentMoviesData currentMoviesData = response.body();

                ArrayList<String> rating = new ArrayList<>();
                ArrayList<String> posterPath = new ArrayList<>();
                ArrayList<String> id = new ArrayList<>();

                //GET RATINGS, POSTERPATH AND MOVIE ID OF EACH MOVIE AND STORE IN ARRAYLIST
                for (int i = 0; i < 20; i++) {
                    rating.add(i, currentMoviesData.getResults()[i].getRating());
                    posterPath.add(i, POSTER_PATH + currentMoviesData.getResults()[i].getPoster_path());
                    id.add(i, currentMoviesData.getResults()[i].getId());
                }

                adapter = new MovieAdapter(posterPath, rating, id, mContext);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CurrentMoviesData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
