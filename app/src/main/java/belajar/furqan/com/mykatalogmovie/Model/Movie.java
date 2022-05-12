package belajar.furqan.com.mykatalogmovie.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import belajar.furqan.com.mykatalogmovie.Database.DatabaseContract;

import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.getColumnString;

public class Movie implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @SerializedName("release_date")
    private String releaseDate;

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        posterPath = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(posterPath);
    }
    public Movie(Cursor cursor) {
        this.id = Integer.parseInt(getColumnString(cursor,  DatabaseContract.MOVIE_ELEMENT.ID));
        this.title = getColumnString(cursor, DatabaseContract.MOVIE_ELEMENT.TITLE);
        this.posterPath = getColumnString(cursor, DatabaseContract.MOVIE_ELEMENT.IMAGE);
        this.overview = getColumnString(cursor, DatabaseContract.MOVIE_ELEMENT.OVERVIEW);
    }
    public Movie(){

    }
}
