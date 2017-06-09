package br.com.lucenasistemas.nastelonas.handler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;
import br.com.lucenasistemas.nastelonas.model.Movie;

/**
 * Created by Weverton on 08/06/2017.
 */

public class JsonMoviesHandler implements DataHandler {

    public final String LOG_TAG = JsonMoviesHandler.class.getSimpleName();

    @Override
    public List<Movie> jsonToModelList(String json) {

        List<Movie> movies = new ArrayList<>();

        try {

            JSONArray results = new JSONObject(json).getJSONArray("results");
            int max = 12;
            for (int i = 0; i < max; i++){
                JSONObject jsonMovie = results.getJSONObject(i);
                int id = (int) jsonMovie.get("id");
                String title = (String) jsonMovie.get("title");
                if (title == null || title.isEmpty() || id == 433422) {
                    max++;
                    continue;
                }
                String poster = (String) jsonMovie.get("poster_path");
                Movie movie = new Movie();
                movie.setId(id);
                movie.setTitle(title);
                movie.setPoster(poster);
                movies.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }

    @Override
    public <T> T jsonToModel(String json) {
        return null;
    }
}
