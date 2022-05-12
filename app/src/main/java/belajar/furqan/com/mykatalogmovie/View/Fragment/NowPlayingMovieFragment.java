package belajar.furqan.com.mykatalogmovie.View.Fragment;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import belajar.furqan.com.mykatalogmovie.Adapter.MovieAdapter;
import belajar.furqan.com.mykatalogmovie.BuildConfig;
import belajar.furqan.com.mykatalogmovie.Model.Movie;
import belajar.furqan.com.mykatalogmovie.Presenter.MoviePresenter;
import belajar.furqan.com.mykatalogmovie.R;
import belajar.furqan.com.mykatalogmovie.Utils.Utils;
import belajar.furqan.com.mykatalogmovie.View.Activity.DetailMovieActivity;
import belajar.furqan.com.mykatalogmovie.View.MovieView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingMovieFragment extends Fragment implements MovieView {

    public static final String TAG = NowPlayingMovieFragment.class.getSimpleName();
    public final static String apiKey = BuildConfig.TMDB_API_KEY;
    private ProgressBar progressBar;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovie;
    public NowPlayingMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing_movie, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progress_bar);
        showProgressBar();
        movieAdapter = new MovieAdapter(movieList, getContext(), this);
        rvMovie.setAdapter(movieAdapter);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        }
        else{
            rvMovie.setLayoutManager(new StaggeredGridLayoutManager(3, 1));
        }
        MoviePresenter moviePresenter = new MoviePresenter(this);
        moviePresenter.getNowPlaying(apiKey, Utils.language);
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
        hideProgressBar();
        movieList.clear();
        movieList.addAll(movie);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {
        Log.d(TAG, "onFailure cause: " + msg);
        Toast.makeText(getContext(), "error :" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void detailMovie(String id,String judul) {
        startActivity(new Intent(getContext(), DetailMovieActivity.class).putExtra("idMovie", id).putExtra("title",judul));
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}
