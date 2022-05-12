package belajar.furqan.com.mykatalogmovie.Reminder;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyMovieService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        //getReleaseToday(params);
        // jobFinished(params,false);
        return false;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
//    private void getReleaseToday(JobParameters job) {
//        Calendar calendar = Calendar.getInstance();
//        mdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        final String strTime = timeFormat.format(calendar.getTime());
//        final String strDate = mdFormat.format(calendar.getTime());
//        Log.d(TAG, "getReleaseTime: " + timeFormat.format(calendar.getTime()));
//        Log.d(TAG, "getReleaseTodayDate: " + strDate);
//
//        if (strTime.equals("08:00")) {
//            serviceMovie.getAPI().getUpComing(apiKey, language).enqueue(new Callback<MovieRespons>() {
//                @Override
//                public void onResponse(Call<MovieRespons> call, Response<MovieRespons> response) {
//                    if (response.code() == 200) {
//                        i = 0;
//                        for (Movie movie : response.body().getMovieList()) {
//                            try {
//                                date1 = mdFormat.parse(strDate);
//                                date2 = mdFormat.parse(movie.getReleaseDate());
//                                Log.d(TAG, "compareDate: " + date1.equals(date2));
//                                if (date1.equals(date2)) {
//                                    showNotification(getApplicationContext(), "Catalogue Movie",
//                                            movie.getTitle() + " is release today " + movie.getReleaseDate(), 100, movie.getId());
//                                    i++;
//                                }
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                            Log.d(TAG, "data nya adalah :" + movie.getReleaseDate() + "1");
//                        }
//                        if (i == 0) {
//                            int index = new Random().nextInt(response.body().getMovieList().size());
//                            showNotification(getApplicationContext(), "Catalogue Movie",
//                                    "tidak ada release hari ini, saksikan " + response.body().getMovieList().get(index).getTitle(), 100,
//                                    response.body().getMovieList().get(index).getId());
//                        }
//                    } else {
//                        Log.d(TAG, "gagal dapat data ga tau knp: ");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<MovieRespons> call, Throwable t) {
//                    Log.d(TAG, "gagal dapat data error");
//                }
//            });
//        }
//        jobFinished(job, false);
//    }
//
//    private void showNotification(Context context, String title, String message, int notifId, int idMovie) {
//        String CHANNEL_ID = "Channel_1";
//        String CHANNEL_NAME = "Job scheduler channel";
//        NotificationCompat.Builder builder;
//        Intent intent = new Intent(this, DetailMovieActivity.class);
//        intent.putExtra("idMovie", String.valueOf(idMovie));
//        intent.putExtra("title", title);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 200, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder = new NotificationCompat.Builder(context)
//                .setContentTitle(title)
//                .setSmallIcon(R.drawable.ic_favorite_black_24dp)
//                .setContentText(message)
//                .setColor(ContextCompat.getColor(context, android.R.color.black))
//                .setGroup("movie")
//                .setContentIntent(pendingIntent)
//                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
//                .setSound(alarmSound)
//                .setAutoCancel(true);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
//                    CHANNEL_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            channel.enableVibration(true);
//            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
//            builder.setChannelId(CHANNEL_ID);
//            if (notificationManagerCompat != null) {
//                notificationManagerCompat.createNotificationChannel(channel);
//            }
//        }
//        Notification notification = builder.build();
//
//        if (notificationManagerCompat != null) {
//            notificationManagerCompat.notify(notifId, notification);
//        }
//    }

}
