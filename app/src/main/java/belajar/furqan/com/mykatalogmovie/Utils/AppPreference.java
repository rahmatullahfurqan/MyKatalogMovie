package belajar.furqan.com.mykatalogmovie.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import belajar.furqan.com.mykatalogmovie.R;

public class AppPreference {
    private SharedPreferences prefs;
    private Context context;

    public AppPreference(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setDailyReminder(Boolean isChecked) {
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.reminder_daily);
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
    public void setUpComingReminder(Boolean isChecked) {
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.up_coming);
        editor.putBoolean(key, isChecked);
        editor.apply();
    }
    public boolean getDailyReminder(){
        String key = context.getResources().getString(R.string.reminder_daily);
        return prefs.getBoolean(key,false);
    }

    public boolean getUpComingReminder(){
        String key = context.getResources().getString(R.string.up_coming);
        return prefs.getBoolean(key,false);
    }
}
