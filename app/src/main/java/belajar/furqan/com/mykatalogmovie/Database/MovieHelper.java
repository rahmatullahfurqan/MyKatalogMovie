package belajar.furqan.com.mykatalogmovie.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.*;
import static belajar.furqan.com.mykatalogmovie.View.Fragment.NowPlayingMovieFragment.TAG;

public class MovieHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    public MovieHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    //ambil all data dalam provider
    public Cursor queryProvider() {
        return database.query(TABLE_MOVIE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    //insert data ke dalam provider
    public long insertProvider(ContentValues values) {
        Log.d(TAG, "insertdataonhelper: dijalankan");
        return database.insert(TABLE_MOVIE, null, values);
    }

    //delete provider berdasarkan id
    public int deleteProvider(String id) {
        return database.delete(TABLE_MOVIE, ID + " = ?", new String[]{id});
    }

    //ambil data berdasarkan id
    public Cursor queryByIdProvider(String id) {
        Log.d(TAG, "getdatabyidonhelper: dijalankan");
        return database.query(TABLE_MOVIE, null
                , ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }
}
