package br.com.lucenasistemas.nastelonas.interfaces;

import java.util.List;

import br.com.lucenasistemas.nastelonas.wrapper.MoviesWrapper;

/**
 * Created by Weverton on 08/06/2017.
 */

public interface DataHandler {

    public <T> MoviesWrapper jsonToModelList(String json);

    public <T> T jsonToModel(String json);

    public enum Tipo {
        MOVIES,
        MOVIE
    }
}
