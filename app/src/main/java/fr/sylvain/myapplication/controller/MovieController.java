package fr.sylvain.myapplication.controller;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fr.sylvain.myapplication.model.Movie;
import fr.sylvain.myapplication.model.MovieRestAPI;
import fr.sylvain.myapplication.view.MovieActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieController {

    private final MovieActivity mainActivity;

    public MovieController(MovieActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onCreate() {
        //mainActivity.showLoader();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gobalasingham.github.io/GhibliMovies.github.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre interface MovieRestAPI
        MovieRestAPI movieRestApi = retrofit.create(MovieRestAPI.class);

        //On récupére un objet call.
        Call<Movie> call = movieRestApi.getMovie(mainActivity.getId());

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Log.d("YOOY", response.message());
                Movie listMovie = response.body();
                mainActivity.showMovie(listMovie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Erreur", "API ERROR");
                t.printStackTrace();
            }
        });
    }

}
