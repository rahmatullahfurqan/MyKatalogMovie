package belajar.furqan.com.mykatalogmovie.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import belajar.furqan.com.mykatalogmovie.Database.MovieHelper;

import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.AUTHORITY;
import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.CONTENT_URI;
import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.TABLE_MOVIE;
import static belajar.furqan.com.mykatalogmovie.View.Fragment.NowPlayingMovieFragment.TAG;


public class MovieProvider extends ContentProvider {
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieHelper movieHelper;
    static {

        sUriMatcher.addURI(AUTHORITY, TABLE_MOVIE, MOVIE);

        sUriMatcher.addURI(AUTHORITY,
                TABLE_MOVIE + "/#",
                MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        movieHelper.open();
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                Log.d(TAG, "getdatafull: dijalankan");
                cursor = movieHelper.queryProvider();
                break;
            case MOVIE_ID:
                Log.d(TAG, "getdatabyid: dijalankan");
                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        Log.d(TAG, "getdata : direturn");
        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long added;
        switch (sUriMatcher.match(uri)){
            case MOVIE :
                Log.d(TAG, "setdata: dijalankan");
                added = movieHelper.insertProvider(values);
                break;
            default:
                added =0;
                break;
        }
        if (added>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int delete ;
        switch (sUriMatcher.match(uri)){
            case MOVIE_ID :
                delete = movieHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                delete=0;
                break;
        }
        if (delete>0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return delete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}