package belajar.furqan.com.mykatalogmovie.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "belajar.furqan.com.mykatalogmovie";
    private DatabaseContract(){}
    public static final class MOVIE_ELEMENT implements BaseColumns {
        public static String TABLE_MOVIE = "table_movie";
        public static String ID = "id";
        public static String IMAGE = "image";
        public static String TITLE = "title";
        public static String OVERVIEW= "overview";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/" + TABLE_MOVIE);
    }
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

}
