package br.com.lucenasistemas.nastelonas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Weverton on 08/06/2017.
 */

public class Movie implements Serializable {

    private int id;

    private String title;

    private int duracao;

    private Double avaliacao;

    private String lancamento;

    private String sinopse;

    private String poster;

    private String trailerName;

    private String trailerKey;

    public Movie(){

    }

    public Movie(int id, String title, String poster) {
        this.id = id;
        this.title = title;

    }

    public static List<Movie> getSampleList(){
        List<Movie> sampleList = new ArrayList<>();
        sampleList.add(new Movie(1,"","https://image.tmdb.org/t/p/w185_and_h278_bestv2/bhzoXeQtfhkjEAyN3Q5UGXdNSS0.jpg"));
        sampleList.add(new Movie(2,"","https://image.tmdb.org/t/p/w185_and_h278_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"));
        sampleList.add(new Movie(3,"","https://image.tmdb.org/t/p/w300_and_h450_bestv2/xHGwFcaWy6Ng6MagII45P3w0wOw.jpg"));
        sampleList.add(new Movie(4,"","https://image.tmdb.org/t/p/w300_and_h450_bestv2/38RVo4cX1O7Ia6k9WXcxkxprHm.jpg"));
        sampleList.add(new Movie(5,"","https://image.tmdb.org/t/p/w185_and_h278_bestv2/bhzoXeQtfhkjEAyN3Q5UGXdNSS0.jpg"));
        sampleList.add(new Movie(6,"","https://image.tmdb.org/t/p/w185_and_h278_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"));
        sampleList.add(new Movie(7,"","https://image.tmdb.org/t/p/w300_and_h450_bestv2/xHGwFcaWy6Ng6MagII45P3w0wOw.jpg"));
        sampleList.add(new Movie(8,"","https://image.tmdb.org/t/p/w300_and_h450_bestv2/38RVo4cX1O7Ia6k9WXcxkxprHm.jpg"));
        return sampleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getLancamento() {
        if (lancamento == null)
            return "";
        return lancamento.substring(0,4);
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public void setTrailerName(String trailerName) {
        this.trailerName = trailerName;
    }

    public String getTrailerKey() {
        return trailerKey;
    }

    public void setTrailerKey(String trailerKey) {
        this.trailerKey = trailerKey;
    }
}
