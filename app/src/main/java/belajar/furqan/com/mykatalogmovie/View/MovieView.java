package belajar.furqan.com.mykatalogmovie.View;

import java.util.List;

import belajar.furqan.com.mykatalogmovie.Model.Movie;

public interface MovieView {
    void onSuccses(List<Movie> movie);

    void onFailure(String msg);

    void detailMovie(String s, String id);
}
