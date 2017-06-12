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

public class MovieTask extends AsyncTask<String,Void,String> {

    private onLoadMovie listener;
    public final String LOG_TAG = MovieTask.class.getSimpleName();
    private String url = "https://api.themoviedb.org/3/movie/";
    private Context context;




    public MovieTask(Context context,int movieId){
        this.context = context;
        this.url = url + movieId;
        if (context instanceof onLoadMovie) {
            this.listener = (onLoadMovie) context;
        }

    }


    @Override
    protected String doInBackground(String... params) {
        ContentValues values = new ContentValues();
        values.put("api_key","YOUR_API_KEY");
        values.put("language","pt-BR");
        values.put("append_to_response","videos");

        String result = CallWebService.sendGet(values,url);
        Log.i(LOG_TAG,result);
        return result;
    }

    @Override
    protected void onPostExecute(String json) {

        Movie movie = DataHandlerFactory.newDataHandler(DataHandler.Tipo.MOVIE).jsonToModel(json);
        if (listener != null)
            listener.onLoad(movie);

    }

    public interface onLoadMovie {
        void onLoad(Movie movie);
    }
}
