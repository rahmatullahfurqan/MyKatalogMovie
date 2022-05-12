package belajar.furqan.com.mykatalogmovie.Presenter;

import belajar.furqan.com.mykatalogmovie.Model.DetailResponse;
import belajar.furqan.com.mykatalogmovie.Connection.ServiceMovie;
import belajar.furqan.com.mykatalogmovie.View.DetailMovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMoviePresenter {
    private DetailMovieView detailMovieView;
    private ServiceMovie service = new ServiceMovie();

    public DetailMoviePresenter(DetailMovieView detailMovieView) {
        this.detailMovieView = detailMovieView;
    }

    public void getDetailMovie(String id, String apiKey, String language) {
        service.getAPI().getDetailMovie(id, apiKey, language).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                if (response.code() == 200) {
                    detailMovieView.onSuccses(response.body());
                } else {
                    detailMovieView.onFailure("respon code != 200");
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                detailMovieView.onFailure(t.getMessage());
            }
        });
    }
}
