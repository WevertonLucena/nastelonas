package br.com.lucenasistemas.nastelonas.interfaces;

import java.util.List;

/**
 * Created by Weverton on 08/06/2017.
 */

public interface DataHandler {

    public <T> List<T> jsonToModelList(String json);

    public <T> T jsonToModel(String json);

    public enum Tipo {
        MOVIES,
        MOVIE
    }
}
