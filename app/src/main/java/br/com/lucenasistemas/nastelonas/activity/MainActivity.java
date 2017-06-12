package br.com.lucenasistemas.nastelonas.activity;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.lucenasistemas.nastelonas.R;
import br.com.lucenasistemas.nastelonas.adapter.MovieAdapter;
import br.com.lucenasistemas.nastelonas.async.MoviesTask;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.util.StringUtils;
import br.com.lucenasistemas.nastelonas.util.ViewHelper;
import br.com.lucenasistemas.nastelonas.wrapper.MoviesWrapper;

public class MainActivity extends AppCompatActivity implements MoviesTask.onLoadMovies {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private ViewGroup layoutProgress;
    private MovieAdapter adapter;
    private GridView gridView;
    private boolean flag_loading = true;
    private int page = 1;
    private int total_pages = 1;
    List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridview_movies);
        layoutProgress = (ViewGroup) findViewById(R.id.layout_progress_main);
        adapter = new MovieAdapter(this,movieList);
        gridView.setAdapter(adapter);
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

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0)
                {
                    if(!flag_loading)
                    {
                        if(movieList.size() > 100){
                            for(int i = 0 ; i < 20 ; i++) {
                                movieList.remove(i);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        page++;
                        if (page <= total_pages) {
                            Log.i(LOG_TAG, "carregar pagina " + page);
                            init(page);
                        }
                    }
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        init(page);
    }

    private void init(int page){
        if(isOnline()) {
            ContentValues values = new ContentValues();
            String generos = getGenresPreferences();

            if (!generos.equals(""))
                values.put("with_genres", getGenresPreferences());
            values.put("page", page);
            MoviesTask task = new MoviesTask(this, values);
            task.execute();
        }else{
            Toast.makeText(this,"Sem conexão, tente mais tarde.",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onLoad(MoviesWrapper moviesWrapper) {
        if (moviesWrapper.getMovies() != null) {
            movieList.addAll(moviesWrapper.getMovies());
            adapter.notifyDataSetChanged();
            total_pages = moviesWrapper.getTotal_pages();
            Log.i(LOG_TAG, "quantidade de filmes na lista " + movieList.size());
            Log.i(LOG_TAG, "total de paginas " + moviesWrapper.getTotal_pages());
        }

    }

    @Override
    public void setStatus(boolean loading) {
        this.flag_loading = loading;
        if (loading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            ViewHelper.fadeIn(layoutProgress,500);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            ViewHelper.fadeOut(layoutProgress,500);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private String getGenresPreferences(){
        List<String> generos = new ArrayList<>();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean acao = preferences.getBoolean(getString(R.string.pref_acao),true);
        if (acao){
            generos.add(getString(R.string.pref_acao));
        }
        boolean animacao = preferences.getBoolean(getString(R.string.pref_animacao),true);
        if (animacao){
            generos.add(getString(R.string.pref_animacao));
        }
        boolean aventura = preferences.getBoolean(getString(R.string.pref_aventura),true);
        if (aventura){
            generos.add(getString(R.string.pref_aventura));
        }
        boolean comedia = preferences.getBoolean(getString(R.string.pref_comedia),true);
        if (comedia){
            generos.add(getString(R.string.pref_comedia));
        }
        boolean crime = preferences.getBoolean(getString(R.string.pref_crime),true);
        if (crime){
            generos.add(getString(R.string.pref_crime));
        }
        boolean documentario = preferences.getBoolean(getString(R.string.pref_documentario),true);
        if (documentario){
            generos.add(getString(R.string.pref_documentario));
        }
        boolean drama = preferences.getBoolean(getString(R.string.pref_drama),true);
        if (drama){
            generos.add(getString(R.string.pref_drama));
        }
        boolean familia = preferences.getBoolean(getString(R.string.pref_familia),true);
        if (familia){
            generos.add(getString(R.string.pref_familia));
        }
        boolean fantasia = preferences.getBoolean(getString(R.string.pref_fantasia),true);
        if (fantasia){
            generos.add(getString(R.string.pref_fantasia));
        }
        boolean faroeste = preferences.getBoolean(getString(R.string.pref_faroeste),true);
        if (faroeste){
            generos.add(getString(R.string.pref_faroeste));
        }
        boolean ficcao = preferences.getBoolean(getString(R.string.pref_ficcao),true);
        if (ficcao){
            generos.add(getString(R.string.pref_ficcao));
        }
        boolean guerra = preferences.getBoolean(getString(R.string.pref_guerra),true);
        if (guerra){
            generos.add(getString(R.string.pref_guerra));
        }
        boolean historia = preferences.getBoolean(getString(R.string.pref_historia),true);
        if (historia){
            generos.add(getString(R.string.pref_historia));
        }
        boolean misterio = preferences.getBoolean(getString(R.string.pref_misterio),true);
        if (misterio){
            generos.add(getString(R.string.pref_misterio));
        }
        boolean musica = preferences.getBoolean(getString(R.string.pref_musica),true);
        if (musica){
            generos.add(getString(R.string.pref_musica));
        }
        boolean romance = preferences.getBoolean(getString(R.string.pref_romance),true);
        if (romance){
            generos.add(getString(R.string.pref_romance));
        }
        boolean terror = preferences.getBoolean(getString(R.string.pref_terror),true);
        if (terror){
            generos.add(getString(R.string.pref_terror));
        }

        String[] s =  generos.toArray(new String[]{});
        return StringUtils.join(s,"|");
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
