package br.com.lucenasistemas.nastelonas.handler;

import br.com.lucenasistemas.nastelonas.interfaces.DataHandler;

/**
 * Created by Weverton on 08/06/2017.
 */

public class DataHandlerFactory {

    public static DataHandler newDataHandler(DataHandler.Tipo tipo) {

        if (tipo == DataHandler.Tipo.MOVIES)
            return new JsonMoviesHandler();
        if (tipo == DataHandler.Tipo.MOVIE)
            return new JsonMovieHandler();

        return null;
    }
}
