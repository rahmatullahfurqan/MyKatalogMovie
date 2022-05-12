package belajar.furqan.com.mykatalogmovie.View;

import belajar.furqan.com.mykatalogmovie.Model.DetailResponse;

public interface DetailMovieView {
    void onSuccses(DetailResponse detailResponse);

    void onFailure(String msg);
}
