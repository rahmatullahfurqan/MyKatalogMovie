package belajar.furqan.com.mykatalogmovie.View.Fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import belajar.furqan.com.mykatalogmovie.Adapter.MovieAdapter;
import belajar.furqan.com.mykatalogmovie.Database.MovieHelper;
import belajar.furqan.com.mykatalogmovie.Model.Movie;
import belajar.furqan.com.mykatalogmovie.R;
import belajar.furqan.com.mykatalogmovie.View.Activity.DetailMovieActivity;
import belajar.furqan.com.mykatalogmovie.View.MovieView;

import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements MovieView {
    private ProgressBar progressBar;
    private List<Movie> movieList = new ArrayList<>();
    private MovieHelper movieHelper;
    private RecyclerView rvMovie;
    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        showProgressBar();
        loadFavorite();
    }

    private void loadFavorite() {
        movieList.clear();
        Cursor cursorList = getContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        for (int i=0;i<cursorList.getCount();i++)
        {
            cursorList.moveToPosition(i);
            movieList.add(new Movie(cursorList));
        }
        MovieAdapter movieAdapter = new MovieAdapter(movieList, getContext(), this);
        rvMovie.setAdapter(movieAdapter);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        }
        else{
            rvMovie.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        }
        movieAdapter.notifyDataSetChanged();
        hideProgressBar();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        }
        else{
            rvMovie.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        }
    }

    @Override
    public void onSuccses(List<Movie> movie) {
    }

    @Override
    public void onFailure(String msg) {
    }

    @Override
    public void detailMovie(String id,String judul) {
        startActivity(new Intent(getContext(), DetailMovieActivity.class).putExtra("idMovie", id).putExtra("title",judul));
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorite();
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}
