package belajar.furqan.com.favoritemovie;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import belajar.furqan.com.favoritemovie.Adapter.MovieAdapter;
import belajar.furqan.com.favoritemovie.Model.Movie;

import static belajar.furqan.com.favoritemovie.Database.DatabaseContract.AUTHORITY;
import static belajar.furqan.com.favoritemovie.Database.DatabaseContract.MOVIE_ELEMENT.TABLE_MOVIE;

public class MainActivity extends AppCompatActivity implements MovieView {
    private ProgressBar progressBar;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovie = findViewById(R.id.rv_movie);
        progressBar = findViewById(R.id.progress_bar);
        showProgressBar();
        movieAdapter = new MovieAdapter(movieList, getApplicationContext(), this);
        rvMovie.setAdapter(movieAdapter);
        rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        listEntries(getContentResolver());
    }

    @Override
    public void detailMovie(String id, String judul) {
        Toast.makeText(getApplicationContext(),"Film "+judul,Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    private void listEntries(ContentResolver cr) {
        Uri uri = Uri.parse("content://" + AUTHORITY + "/" + TABLE_MOVIE);
        Cursor c = cr.query(uri, null, null, null, null);
        movieList.clear();
        for (int i = 0; i < c.getCount(); i++) {
            c.moveToPosition(i);
            Movie movie = new Movie(c);
            movieList.add(movie);
        }
        movieAdapter.notifyDataSetChanged();
        hideProgressBar();
        c.close();
    }
}

