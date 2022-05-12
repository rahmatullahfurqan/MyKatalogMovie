package belajar.furqan.com.mykatalogmovie.Widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import belajar.furqan.com.mykatalogmovie.Model.Movie;
import belajar.furqan.com.mykatalogmovie.R;

import static belajar.furqan.com.mykatalogmovie.Database.DatabaseContract.MOVIE_ELEMENT.CONTENT_URI;
import static belajar.furqan.com.mykatalogmovie.View.Fragment.NowPlayingMovieFragment.TAG;

public class FavoriteRemoteFactory implements RemoteViewsService.RemoteViewsFactory {
    private final List<Bitmap> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private List<Movie> movieList = new ArrayList<>();

    FavoriteRemoteFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        movieList.clear();
        Cursor cursorList = mContext.getContentResolver().query(CONTENT_URI, null, null, null, null);
        for (int i = 0; i < cursorList.getCount(); i++) {
            cursorList.moveToPosition(i);
            movieList.add(new Movie(cursorList));
            Log.d(TAG, "liat data gambar: " + movieList.get(i).getPosterPath());
        }
        Log.d(TAG, "Sampai kesini gak?: ");
        for (Movie movie : movieList) {
            try {
                Bitmap preview = Glide.with(mContext)
                        .asBitmap()
                        .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                        .apply(new RequestOptions().fitCenter())
                        .submit()
                        .get();
                mWidgetItems.add(preview);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Binder.restoreCallingIdentity(identityToken);
    }


    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get(position));
        Log.d(TAG, "seT GAMBAR ");

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
