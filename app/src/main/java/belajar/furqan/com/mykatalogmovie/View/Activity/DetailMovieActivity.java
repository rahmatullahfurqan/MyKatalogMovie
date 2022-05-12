package belajar.furqan.com.mykatalogmovie.View.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import belajar.furqan.com.mykatalogmovie.Adapter.GenreAdapter;
import belajar.furqan.com.mykatalogmovie.Database.MovieHelper;
import belajar.furqan.com.mykatalogmovie.Model.DetailResponse;
import belajar.furqan.com.mykatalogmovie.Model.GenreMovie;
import belajar.furqan.com.mykatalogmovie.Model.Movie;
import belajar.furqan.com.mykatalogmovie.Presenter.DetailMoviePresenter;
import belajar.furqan.com.mykatalogmovie.R;
import belajar.furqan.com.mykatalogmovie.Utils.Utils;
import belajar.furqan.com.mykatalogmovie.View.DetailMovieView;
import belajar.furqan.com.mykatalogmovie.View.Fragment.SearchMovieFragment;
import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.*;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {
    TextView tvTitle, tvTitle2, tvRating, tvDuration, tvLanguage, tvReleased, tvOverview;
    RecyclerView rvGenre;
    ImageView ivMovie;
    Movie movie;
    ProgressBar progressBar;
    Menu itemMenu = null;
    public static int RESULT_ADD = 101;
    public static int RESULT_DELETE = 301;

    DetailMoviePresenter detailMoviePresenter;
    GenreAdapter genreAdapter;
    Boolean isFavorite = false;
    MovieHelper movieHelper;
    List<GenreMovie> genreMovieList = new ArrayList<>();
    DetailResponse detailResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        }
        movieHelper = new MovieHelper(this);
        ivMovie = findViewById(R.id.iv_movie);
        progressBar = findViewById(R.id.progress_bar);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle2 = findViewById(R.id.tv_title2);
        tvRating = findViewById(R.id.tv_rating);
        tvDuration = findViewById(R.id.tv_duration);
        tvLanguage = findViewById(R.id.tv_language);
        tvReleased = findViewById(R.id.tv_released);
        tvOverview = findViewById(R.id.tv_overview);
        showProgressBar();
        rvGenre = findViewById(R.id.rv_genre);
        genreAdapter = new GenreAdapter(genreMovieList, this);
        rvGenre.setAdapter(genreAdapter);
        rvGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, true));
        detailMoviePresenter = new DetailMoviePresenter(this);
        detailMoviePresenter.getDetailMovie(getIntent().getStringExtra("idMovie"), SearchMovieFragment.apiKey, Utils.language);

    }

    @Override
    public void onSuccses(DetailResponse detailResponse) {
        this.detailResponse = detailResponse;
        tvTitle.setText(detailResponse.getTitle());
        tvTitle2.setText(detailResponse.getTagline());
        tvRating.setText(detailResponse.getRating());
        tvDuration.setText(detailResponse.getDuration());
        if (detailResponse.getLanguageList().size() != 0) {
            tvLanguage.setText(detailResponse.getLanguageList().get(0).getLanguage());
        }
        tvReleased.setText(detailResponse.getRelease());
        tvOverview.setText(detailResponse.getOverview());
        genreMovieList.clear();
        genreMovieList.addAll(detailResponse.getGenres());
        genreAdapter.notifyDataSetChanged();
        if (detailResponse.getPosterPath() != null)
            Picasso.get().load("https://image.tmdb.org/t/p/w185" + detailResponse.getPosterPath()).fit().centerInside().into(ivMovie);
        hideProgressBar();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(getApplicationContext(), "error :" + msg, Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        itemMenu = menu;
        cekFavorite();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favorite) {
            try {
                if (!isFavorite) {
                    addToFavorite();
                    Toast.makeText(this, "set favorite", Toast.LENGTH_SHORT).show();
                } else {
                    removeFavorite();
                    Toast.makeText(this, " remove favorite", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                isFavorite=!isFavorite;
                Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_SHORT).show();
            }
            isFavorite = !isFavorite;
            setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeFavorite() {
        Uri uri = Uri.parse(CONTENT_URI + "/" + detailResponse.getId());
        getContentResolver().delete(uri, null, null);
        setResult(RESULT_DELETE, null);
    }

    private void addToFavorite() {
        ContentValues values = new ContentValues();
        values.put(ID, String.valueOf(detailResponse.getId()));
        values.put(IMAGE, detailResponse.getPosterPath());
        values.put(TITLE, detailResponse.getTitle());
        values.put(OVERVIEW, detailResponse.getOverview());
        getContentResolver().insert(CONTENT_URI, values);
        setResult(RESULT_ADD);
    }

    private void setFavorite() {
        if (isFavorite) {
            itemMenu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        } else {
            itemMenu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        }
    }

    private void cekFavorite() {
        Uri uri = Uri.parse(CONTENT_URI + "/" + getIntent().getStringExtra("idMovie"));
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) movie = new Movie(cursor);
        }
        if (movie != null) {
            isFavorite = true;
        }
        setFavorite();
    }
}
