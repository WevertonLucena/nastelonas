package br.com.lucenasistemas.nastelonas.handler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;
import br.com.lucenasistemas.nastelonas.model.Movie;

/**
 * Created by Weverton on 08/06/2017.
 */

@SuppressWarnings("unchecked")
class JsonMovieHandler implements DataHandler {

    public final String LOG_TAG = JsonMovieHandler.class.getSimpleName();

    @Override
    public Movie jsonToModel(String json) {
        Movie movie = new Movie();

        try {

            JSONObject result = new JSONObject(json);
            int id = (int) result.get("id");
            String title = (String) result.get("title");
            int duracao = (Integer) result.get("runtime");
            Double avaliacao = (Double) result.get("vote_average");
            String lancamento = (String) result.get("release_date");
            String sinopse = (String) result.get("overview");
            String poster = (String) result.get("poster_path");

            JSONArray jsonVideo = result.getJSONObject("videos").getJSONArray("results");

            String trailerKey = (String) jsonVideo.getJSONObject(0).get("key");
            String trailerName = (String) jsonVideo.getJSONObject(0).get("name");
            movie.setId(id);
            movie.setTitle(title);
            movie.setPoster(poster);
            movie.setAvaliacao(avaliacao);
            movie.setDuracao(duracao);
            movie.setLancamento(lancamento);
            movie.setSinopse(sinopse);
            movie.setTrailerKey(trailerKey);
            movie.setTrailerName(trailerName);

            Log.i(LOG_TAG,movie.getTrailerKey());
            Log.i(LOG_TAG,movie.getTrailerName());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;
    }

    @Override
    public List<Movie> jsonToModelList(String json) {

        return null;
    }
}
