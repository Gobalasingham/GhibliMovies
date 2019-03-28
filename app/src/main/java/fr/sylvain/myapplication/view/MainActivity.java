package fr.sylvain.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import fr.sylvain.myapplication.R;
import fr.sylvain.myapplication.controller.MainController;
import fr.sylvain.myapplication.controller.MyAdapter;
import fr.sylvain.myapplication.model.Movie;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        controller = new MainController(this);
        controller.onCreate();
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
    }

    public void showList(List<Movie> list){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(list, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        Intent intentMain = new Intent(MainActivity.this ,
                MovieActivity.class);
        intentMain.putExtra("id",(String) v.getTag());
        MainActivity.this.startActivity(intentMain);
    }
}
