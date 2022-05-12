package belajar.furqan.com.mykatalogmovie.Widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

import static belajar.furqan.com.mykatalogmovie.View.Fragment.NowPlayingMovieFragment.TAG;


public class FavoriteWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(TAG, "sampai service: ");
        return new FavoriteRemoteFactory(this.getApplicationContext());
    }

}
