package br.com.lucenasistemas.nastelonas.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.lucenasistemas.nastelonas.R;
import br.com.lucenasistemas.nastelonas.async.MovieTask;
import br.com.lucenasistemas.nastelonas.model.Movie;
import br.com.lucenasistemas.nastelonas.util.StringUtils;

public class DetailActivity extends AppCompatActivity implements MovieTask.onLoadMovie {

    private final String URL_POSTER = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/";
    private TextView txtTitulo;
    private TextView txtLancamento;
    private TextView txtAvaliacao;
    private TextView txtDuracao;
    private TextView txtSinopse;
    private TextView txtTrailerName;
    private ImageView imgPlay;
    private ImageView imgPoster;
    private ViewGroup layoutTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtTitulo = (TextView) findViewById(R.id.txt_titulo);
        txtLancamento = (TextView) findViewById(R.id.txt_ano);
        txtAvaliacao = (TextView) findViewById(R.id.txt_avaliacao);
        txtDuracao = (TextView) findViewById(R.id.txt_duracao);
        txtSinopse = (TextView) findViewById(R.id.txt_sinopse);
        txtTrailerName = (TextView) findViewById(R.id.txt_trailer_name);
        imgPlay = (ImageView) findViewById(R.id.img_play);
        imgPoster = (ImageView) findViewById(R.id.img_poster);
        layoutTrailer = (ViewGroup) findViewById(R.id.layout_trailer);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        MovieTask task = new MovieTask(this,movie.getId());
        task.execute();
    }

    @Override
    public void onLoad(final Movie movie) {
        txtTitulo.setText(movie.getTitle());
        txtLancamento.setText(movie.getLancamento());
        if(movie.getDuracao() == 0)
            txtDuracao.setVisibility(View.GONE);
        txtDuracao.setText(String.valueOf(movie.getDuracao()) + " min");
        txtAvaliacao.setText(String.valueOf(movie.getAvaliacao()));
        if (StringUtils.isEmpty(movie.getSinopse()))
            txtSinopse.setText("Sinopse não disponível");
        else
            txtSinopse.setText(movie.getSinopse());
        if (StringUtils.isEmpty(movie.getTrailerName()))
            layoutTrailer.setVisibility(View.GONE);
        else
            txtTrailerName.setText(movie.getTrailerName());



        Picasso.with(this).load(URL_POSTER + movie.getPoster()).resize(480,778).into(imgPoster);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movie.getTrailerKey()));
                startActivity(intent);
            }
        });
    }
}
