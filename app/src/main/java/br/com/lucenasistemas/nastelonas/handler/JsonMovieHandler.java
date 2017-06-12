package br.com.lucenasistemas.nastelonas.handler;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.wrapper.MoviesWrapper;

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
            int duracao = 0;
            Double avaliacao = 0D;
            String lancamento = "";
            String sinopse = "";
            String poster = "";
            JSONObject result = new JSONObject(json);
            int id = (int) result.get("id");
            String title = (String) result.get("title");
            if (!result.isNull("runtime"))
                duracao = (Integer) result.get("runtime");
            if (!result.isNull("vote_average"))
                avaliacao = (Double) result.get("vote_average");
            if (!result.isNull("release_date"))
                lancamento = (String) result.get("release_date");
            if (!result.isNull("overview"))
                sinopse = (String) result.get("overview");
            if (!result.isNull("poster_path"))
            poster = (String) result.get("poster_path");

            JSONArray jsonVideo = result.getJSONObject("videos").getJSONArray("results");

            String trailerKey = "";
            String trailerName = "";
            try {
                trailerKey = (String) jsonVideo.getJSONObject(0).get("key");
                trailerName = (String) jsonVideo.getJSONObject(0).get("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
    public MoviesWrapper jsonToModelList(String json) {

        return null;
    }
}
