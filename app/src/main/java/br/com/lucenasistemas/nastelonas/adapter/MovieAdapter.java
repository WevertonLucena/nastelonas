package br.com.lucenasistemas.nastelonas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.lucenasistemas.nastelonas.R;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.util.ImageHelper;

/**
 * Created by Weverton on 08/06/2017.
 */

public final class MovieAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private List<Movie> movies = new ArrayList<>();
    private Context context;
    private final String URL_POSTER = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/";
    Picasso picasso;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        picasso = Picasso.with(context);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie  getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView poster;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item_movie, parent, false);
            v.setTag(R.id.movie_poster, v.findViewById(R.id.movie_poster));
        }

        poster = (ImageView) v.getTag(R.id.movie_poster);
        Movie movie = getItem(position);

        picasso.with(context).load(URL_POSTER + movie.getPoster()).into(poster);

        return v;
    }
}
