package fr.sylvain.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import fr.sylvain.myapplication.R;
import fr.sylvain.myapplication.controller.MovieController;
import fr.sylvain.myapplication.controller.MyAdapter;
import fr.sylvain.myapplication.model.Movie;

public class MovieActivity extends AppCompatActivity {

    private String id;

    private TextView title;

    private TextView desc;
    private TextView real;
    private ImageView affiche;
    private MovieController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        id =  getIntent().getStringExtra("id");

        title = (TextView) findViewById(R.id.title);
        real = (TextView) findViewById(R.id.real);
        desc = (TextView) findViewById(R.id.description);
        affiche = (ImageView) findViewById(R.id.affiche);

        controller = new MovieController(this);
        controller.onCreate();
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
    }

    public void showMovie(Movie movie){

        title.setText(String.format(Locale.US,"%s (%d)", movie.getTitle(), movie.getRelease_date()));
        real.setText(new StringBuilder().append("by ").append(movie.getDirector()).toString());
        Picasso.get().load(movie.getImage()).into(affiche);
        desc.setText(movie.getDescription());
    }

    public String getId() {
        return id;
    }

}
