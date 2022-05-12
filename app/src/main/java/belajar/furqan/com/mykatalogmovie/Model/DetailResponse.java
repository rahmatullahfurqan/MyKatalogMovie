package belajar.furqan.com.mykatalogmovie.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResponse {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    @SerializedName("tagline")
    private String tagline;

    @SerializedName("vote_average")
    private String rating;

    @SerializedName("release_date")
    private String release;

    @SerializedName("overview")
    private String overview;

    @SerializedName("genres")
    private List<GenreMovie> genres;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("title")
    private String title;

    @SerializedName("runtime")
    private String duration;

    @SerializedName("spoken_languages")
    private List<Language> languageList;

    public List<GenreMovie> getGenres() {
        return genres;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Language> languageList) {
        this.languageList = languageList;
    }

    public void setGenres(List<GenreMovie> genres) {
        this.genres = genres;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
