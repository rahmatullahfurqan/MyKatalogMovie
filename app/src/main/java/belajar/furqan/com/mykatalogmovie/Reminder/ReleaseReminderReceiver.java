package belajar.furqan.com.mykatalogmovie.Reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import belajar.furqan.com.mykatalogmovie.Connection.ServiceMovie;
import belajar.furqan.com.mykatalogmovie.Model.Movie;
import belajar.furqan.com.mykatalogmovie.Model.MovieRespons;
import belajar.furqan.com.mykatalogmovie.R;
import belajar.furqan.com.mykatalogmovie.View.Activity.DetailMovieActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static belajar.furqan.com.mykatalogmovie.Utils.Utils.language;
import static belajar.furqan.com.mykatalogmovie.View.Fragment.NowPlayingMovieFragment.TAG;
import static belajar.furqan.com.mykatalogmovie.View.Fragment.SearchMovieFragment.apiKey;

public class ReleaseReminderReceiver extends BroadcastReceiver {
    private static final int ID_REPEATING = 11;
    Context context;
    ServiceMovie serviceMovie = new ServiceMovie();
    SimpleDateFormat mdFormat;
    int i =0;

    public ReleaseReminderReceiver(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        getReleaseToday();
    }
    public void setRepeatingRelease(Context context,String time) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminderReceiver.class);
        String timeArray[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    private void showNotification(Context context, String title, String message, int notifId, int idMovie) {
        String CHANNEL_ID = "Channel_2";
        String CHANNEL_NAME = "Job scheduler channel";
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra("idMovie", String.valueOf(idMovie));
        intent.putExtra("title", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_favorite_black_24dp)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setGroup("movie")
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }
        Notification notification = builder.build();
        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }
    }
    private void getReleaseToday() {
        Calendar calendar = Calendar.getInstance();
        mdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        final String strDate = mdFormat.format(calendar.getTime());
        Log.d(TAG, "getReleaseTodayDate: " + strDate);

            serviceMovie.getAPI().getUpComing(apiKey, language).enqueue(new Callback<MovieRespons>() {
                @Override
                public void onResponse(Call<MovieRespons> call, Response<MovieRespons> response) {
                    if (response.code() == 200) {
                        i = 0;
                        for (Movie movie : response.body().getMovieList()) {
                            try {
                                Date date1 = mdFormat.parse(strDate);
                                Date date2 = mdFormat.parse(movie.getReleaseDate());
                                Log.d(TAG, "compareDate: " + date1.equals(date2));
                                if (date1.equals(date2)) {
                                    showNotification(context, "Catalogue Movie",
                                            movie.getTitle() + " has been release today " + movie.getReleaseDate(), ID_REPEATING, movie.getId());
                                    i++;
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "data nya adalah :" + movie.getReleaseDate() + "1");
                        }
                        if (i == 0) {
                            int index = new Random().nextInt(response.body().getMovieList().size());
                            showNotification(context, "Catalogue Movie",
                                    "tidak ada release hari ini, saksikan " + response.body().getMovieList().get(index).getTitle(), ID_REPEATING,
                                    response.body().getMovieList().get(index).getId());
                        }
                    } else {
                        Log.d(TAG, "gagal dapat data ga tau knp: ");
                    }
                }

                @Override
                public void onFailure(Call<MovieRespons> call, Throwable t) {
                    Log.d(TAG, "gagal dapat data error");
                }
            });
        }
    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminderReceiver.class);

        int requestCode = ID_REPEATING;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                requestCode,
                intent,
                0);

        alarmManager.cancel(pendingIntent);
    }
}
