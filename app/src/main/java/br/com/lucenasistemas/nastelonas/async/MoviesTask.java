package br.com.lucenasistemas.nastelonas.async;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import br.com.lucenasistemas.nastelonas.handler.DataHandlerFactory;
import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.util.CallWebService;

/**
 * Created by Weverton on 08/06/2017.
 */

public class MoviesTask extends AsyncTask<String,Void,String> {

    private onLoadMovies listener;
    public final String LOG_TAG = MoviesTask.class.getSimpleName();
    private final String URL = "https://api.themoviedb.org/3/discover/movie";
    private Context context;

    public MoviesTask(Context context){
        this.context = context;
        if (context instanceof onLoadMovies) {
            this.listener = (onLoadMovies) context;
        }

    }


    @Override
    protected String doInBackground(String... params) {
        ContentValues values = new ContentValues();
        values.put("primary_release_date.gte","2017-03-01");
        values.put("primary_release_date.lte","2017-06-08");
        values.put("api_key","xxx");
        values.put("language","pt-BR");
        values.put("sort_by","popularity.desc");

        String result = CallWebService.sendGet(values,URL);
        Log.i(LOG_TAG,result);
        return result;
    }

    @Override
    protected void onPostExecute(String json) {

        List<Movie> movies = DataHandlerFactory.newDataHandler(DataHandler.Tipo.MOVIES).jsonToModelList(json);
        if (listener != null)
            listener.onLoad(movies);

    }

    public interface onLoadMovies{
        void onLoad(List<Movie> movies);
    }
}
