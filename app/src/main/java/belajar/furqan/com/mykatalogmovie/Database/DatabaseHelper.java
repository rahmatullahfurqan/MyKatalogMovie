package belajar.furqan.com.mykatalogmovie.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_movie";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_MOVIE = String.format(
                    "CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.MOVIE_ELEMENT.TABLE_MOVIE,
            DatabaseContract.MOVIE_ELEMENT._ID,
            DatabaseContract.MOVIE_ELEMENT.ID,
            DatabaseContract.MOVIE_ELEMENT.IMAGE,
            DatabaseContract.MOVIE_ELEMENT.TITLE,
            DatabaseContract.MOVIE_ELEMENT.OVERVIEW
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
}
