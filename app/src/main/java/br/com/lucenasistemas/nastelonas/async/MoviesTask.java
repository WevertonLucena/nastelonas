package br.com.lucenasistemas.nastelonas.async;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.lucenasistemas.nastelonas.handler.DataHandlerFactory;
import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.util.CallWebService;
import br.com.lucenasistemas.nastelonas.wrapper.MoviesWrapper;

/**
 * Created by Weverton on 08/06/2017.
 */

public class MoviesTask extends AsyncTask<String,Void,String> {
    private ContentValues values;
    private onLoadMovies listener;
    public final String LOG_TAG = MoviesTask.class.getSimpleName();
    private final String URL = "https://api.themoviedb.org/3/discover/movie";
    private Context context;

    public MoviesTask(Context context, ContentValues values){
        this.context = context;
        this.values = values;
        if (context instanceof onLoadMovies) {
            this.listener = (onLoadMovies) context;
        }

    }


    @Override
    protected String doInBackground(String... params) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date hoje = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(hoje);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 4);
        String inicio = format.format(c.getTime());
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 6);
        String fim  = format.format(c.getTime());

        values.put("primary_release_date.gte",inicio);
        values.put("primary_release_date.lte",fim);
        values.put("api_key","YOUR_API_KEY");
        values.put("language","pt-BR");
        values.put("sort_by","popularity.desc");

        String result = CallWebService.sendGet(values,URL);
        return result;
    }

    @Override
    protected void onPreExecute() {
        if (listener != null)
            listener.setStatus(true);
    }

    @Override
    protected void onPostExecute(String json) {

        MoviesWrapper movies = DataHandlerFactory.newDataHandler(DataHandler.Tipo.MOVIES).jsonToModelList(json);
        if (listener != null) {
            listener.onLoad(movies);
            listener.setStatus(false);
        }

    }

    public interface onLoadMovies{
        void onLoad(MoviesWrapper moviesWrapper);
        void setStatus(boolean loading);
    }
}
