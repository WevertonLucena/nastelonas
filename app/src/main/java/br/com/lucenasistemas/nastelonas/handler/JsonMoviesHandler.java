package br.com.lucenasistemas.nastelonas.handler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.wrapper.MoviesWrapper;

/**
 * Created by Weverton on 08/06/2017.
 */

public class JsonMoviesHandler implements DataHandler {

    public final String LOG_TAG = JsonMoviesHandler.class.getSimpleName();

    @Override
    public MoviesWrapper jsonToModelList(String json) {

        //Log.i(LOG_TAG,StringUtils.removerCaracteresEspeciais(json));
        MoviesWrapper moviesWrapper =  new MoviesWrapper();
        List<Movie> movies = new ArrayList<>();

        try {

            JSONArray results = new JSONObject(json).getJSONArray("results");
            moviesWrapper.setTotal_pages(new JSONObject(json).getInt("total_pages"));
            int max = 20;
            for (int i = 0; i < results.length(); i++){
                JSONObject jsonMovie = results.getJSONObject(i);
                int id = (int) jsonMovie.get("id");
                String title = (String) jsonMovie.get("title");
                if(jsonMovie.isNull("poster_path")){
                    continue;
                }
                String poster = (String) jsonMovie.get("poster_path");
                Movie movie = new Movie();
                movie.setId(id);
                movie.setTitle(title);
                movie.setPoster(poster);
                movies.add(movie);
            }
            moviesWrapper.setMovies(movies);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return moviesWrapper;
    }

    @Override
    public <T> T jsonToModel(String json) {
        return null;
    }
}
