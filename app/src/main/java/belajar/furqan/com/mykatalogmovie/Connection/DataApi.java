package belajar.furqan.com.mykatalogmovie.Connection;


import belajar.furqan.com.mykatalogmovie.Model.DetailResponse;
import belajar.furqan.com.mykatalogmovie.Model.MovieRespons;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataApi {
    @GET("search/movie")
    Call<MovieRespons> getMovie(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query);

    @GET("movie/{id}")
    Call<DetailResponse> getDetailMovie(@Path("id") String id, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/now_playing")
    Call<MovieRespons> getNowPlaying(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/upcoming")
    Call<MovieRespons> getUpComing(@Query("api_key") String apiKey, @Query("language") String language);
}
