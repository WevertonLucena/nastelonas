package br.com.lucenasistemas.nastelonas.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import br.com.lucenasistemas.nastelonas.R;
import br.com.lucenasistemas.nastelonas.adapter.MovieAdapter;
import br.com.lucenasistemas.nastelonas.async.MoviesTask;
import br.com.lucenasistemas.nastelonas.model.Movie;

public class MainActivity extends AppCompatActivity implements MoviesTask.onLoadMovies {

    public final String LOG_TAG = MainActivity.class.getSimpleName();
    private MovieAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview_movies);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("movie",movie);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this)
                        .toBundle();
                startActivity(intent,bundle);
            }
        });

        MoviesTask task = new MoviesTask(this);
        task.execute();
    }

    @Override
    public void onLoad(List<Movie> movies) {
        adapter = new MovieAdapter(this, movies);
        gridView.setAdapter(adapter);
    }
}
