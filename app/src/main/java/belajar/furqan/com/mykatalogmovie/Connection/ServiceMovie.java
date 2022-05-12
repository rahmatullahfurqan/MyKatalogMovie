package belajar.furqan.com.mykatalogmovie.Connection;

import java.util.concurrent.TimeUnit;

import belajar.furqan.com.mykatalogmovie.Connection.DataApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceMovie {
    private Retrofit retrofit = null;

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public DataApi getAPI() {
        String BASE_URL = "https://api.themoviedb.org/3/";
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(DataApi.class);
    }
}
