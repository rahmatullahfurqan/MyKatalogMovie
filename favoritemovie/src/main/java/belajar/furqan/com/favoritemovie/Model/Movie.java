package belajar.furqan.com.favoritemovie.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import belajar.furqan.com.favoritemovie.Database.DatabaseContract;

import static belajar.furqan.com.favoritemovie.Database.DatabaseContract.getColumnString;

public class Movie implements Parcelable {

    private int id;

    private String title;

    private String overview;

    private String posterPath;

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

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
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
}
