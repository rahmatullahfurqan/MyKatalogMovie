package belajar.furqan.com.mykatalogmovie.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import belajar.furqan.com.mykatalogmovie.Reminder.AlarmReceiver;
import belajar.furqan.com.mykatalogmovie.Reminder.ReleaseReminderReceiver;
import belajar.furqan.com.mykatalogmovie.Utils.AppPreference;
import belajar.furqan.com.mykatalogmovie.R;

public class SettingReminderActivity extends AppCompatActivity{
    private static final String TAG ="setting reminder" ;
    AppPreference appPreference;
    Switch dailyReminder, releaseReminder;
    AlarmReceiver alarmReceiver = new AlarmReceiver();
    ReleaseReminderReceiver releaseReminderReceiver = new ReleaseReminderReceiver();
    String onceDate;
    String onceTime = "07:00";
    private int jobId = 10;
    String onceMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_reminder);
        dailyReminder = findViewById(R.id.remnder_daily);
        releaseReminder = findViewById(R.id.remnder_up_coming);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        onceDate = mdFormat.format(calendar.getTime());
        onceMessage = getResources().getString(R.string.miss_daily);
        appPreference = new AppPreference(this);
        dailyReminder.setChecked(appPreference.getDailyReminder());
        releaseReminder.setChecked(appPreference.getUpComingReminder());
        releaseReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appPreference.setUpComingReminder(isChecked);
                if (isChecked){
                    setUpReleaseReminder();
                    Toast.makeText(getApplicationContext(), getString(R.string.reminder_on), Toast.LENGTH_SHORT).show();
                }
                else {
                    cancelReleaseReminder();
                    Toast.makeText(getApplicationContext(), getString(R.string.reminder_off), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dailyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appPreference.setDailyReminder(isChecked);
                if (isChecked){
                    setUpDailyreminder();
                    Toast.makeText(getApplicationContext(), getString(R.string.reminder_on), Toast.LENGTH_SHORT).show();
                }
                else {
                    cancelDailyReminder();
                    Toast.makeText(getApplicationContext(), getString(R.string.reminder_off), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setUpReleaseReminder() {
            releaseReminderReceiver.setRepeatingRelease(SettingReminderActivity.this, "8:00");
        }

    public void setUpDailyreminder() {
        alarmReceiver.setRepeatingAlarm(SettingReminderActivity.this, AlarmReceiver.TYPE_REPEATING,
                onceTime,
                onceMessage);
    }

    public void cancelReleaseReminder() {
        releaseReminderReceiver.cancelAlarm(SettingReminderActivity.this);
    }

    public void cancelDailyReminder() {
        alarmReceiver.cancelAlarm(SettingReminderActivity.this);
    }


//    private void startJob() {
//        ComponentName mServiceComponent = new ComponentName(this, MyMovieService.class);
//        JobInfo.Builder builder = new JobInfo.Builder(jobId, mServiceComponent);
//        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//        builder.setRequiresDeviceIdle(false);
//        builder.setRequiresCharging(false);
//        builder.setPeriodic(10000);
//        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        jobScheduler.schedule(builder.build());
//        Toast.makeText(this, getString(R.string.reminder_on), Toast.LENGTH_SHORT).show();
//    }
//    private void cancelJob() {
//        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        tm.cancel(jobId);
//        Toast.makeText(this, getString(R.string.reminder_off), Toast.LENGTH_SHORT).show();
//    }
}
