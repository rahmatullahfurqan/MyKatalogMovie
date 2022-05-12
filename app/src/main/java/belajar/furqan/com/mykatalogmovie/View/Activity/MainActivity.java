package belajar.furqan.com.mykatalogmovie.View.Activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import belajar.furqan.com.mykatalogmovie.R;
import belajar.furqan.com.mykatalogmovie.View.Fragment.FavoriteFragment;
import belajar.furqan.com.mykatalogmovie.View.Fragment.NowPlayingMovieFragment;
import belajar.furqan.com.mykatalogmovie.View.Fragment.SearchMovieFragment;
import belajar.furqan.com.mykatalogmovie.View.Fragment.UpComingMovieFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.now_playing:
                gotoFragment(new NowPlayingMovieFragment(), getString(R.string.now_playing));
                break;
            case R.id.up_coming:
                gotoFragment(new UpComingMovieFragment(), getString(R.string.up_coming));
                break;
            case R.id.search_movie:
                gotoFragment(new SearchMovieFragment(), getString(R.string.search_movie));
                break;
            case R.id.favorite:
                gotoFragment(new FavoriteFragment(), getString(R.string.favorite));
                break;
        }
        return true;
    }

    void gotoFragment(Fragment fragment, String titleToolbar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleToolbar);
        }
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_menu, fragment).addToBackStack("tag");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        else if (item.getItemId() == R.id.action_change_reminder) {
            Intent mIntent = new Intent(getApplicationContext(), SettingReminderActivity.class);
            startActivity(mIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
