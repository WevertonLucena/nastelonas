package br.com.lucenasistemas.nastelonas.wrapper;

import java.util.List;

import br.com.lucenasistemas.nastelonas.model.Movie;

/**
 * Created by Weverton on 11/06/2017.
 */

public class MoviesWrapper {

    private List<Movie> movies;
    private int page;
    private int total_pages;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
