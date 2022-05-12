package belajar.furqan.com.mykatalogmovie.Presenter;

import belajar.furqan.com.mykatalogmovie.Model.MovieRespons;
import belajar.furqan.com.mykatalogmovie.Connection.ServiceMovie;
import belajar.furqan.com.mykatalogmovie.View.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {
    private MovieView movieView;
    private ServiceMovie service = new ServiceMovie();

    public MoviePresenter(MovieView movieView) {
        this.movieView = movieView;
    }

    public void getSearchMovie(String apiKey, String language, String query) {
        service.getAPI().getMovie(apiKey, language, query).enqueue(new Callback<MovieRespons>() {
            @Override
            public void onResponse(Call<MovieRespons> call, Response<MovieRespons> response) {
                if (response.code() == 200) {
                    movieView.onSuccses(response.body().getMovieList());
                } else {
                    movieView.onFailure("respon code != 200");
                }
            }

            @Override
            public void onFailure(Call<MovieRespons> call, Throwable t) {
                movieView.onFailure(t.getMessage());
            }
        });
    }

    public void getNowPlaying(String apiKey, String language) {
        service.getAPI().getNowPlaying(apiKey, language).enqueue(new Callback<MovieRespons>() {
            @Override
            public void onResponse(Call<MovieRespons> call, Response<MovieRespons> response) {
                if (response.code() == 200) {
                    movieView.onSuccses(response.body().getMovieList());
                } else {
                    movieView.onFailure("respon code != 200");
                }
            }

            @Override
            public void onFailure(Call<MovieRespons> call, Throwable t) {
                movieView.onFailure(t.getMessage());
            }
        });
    }

    public void getUpComing(String apiKey, String language) {
        service.getAPI().getUpComing(apiKey, language).enqueue(new Callback<MovieRespons>() {
            @Override
            public void onResponse(Call<MovieRespons> call, Response<MovieRespons> response) {
                if (response.code() == 200) {
                    movieView.onSuccses(response.body().getMovieList());
                } else {
                    movieView.onFailure("respon code != 200");
                }
            }

            @Override
            public void onFailure(Call<MovieRespons> call, Throwable t) {
                movieView.onFailure(t.getMessage());
            }
        });
    }
}
