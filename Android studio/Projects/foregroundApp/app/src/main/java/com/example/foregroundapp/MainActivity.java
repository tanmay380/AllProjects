package com.example.foregroundapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AppOpsManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.rvalerio.fgchecker.AppChecker;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestUsageStatsPermissions();
        scheduleJob();
//        startService(new Intent(this, backServie.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, backServie.class);
        JobInfo jobInfo = new JobInfo.Builder(1234, componentName)
                .setRequiresBatteryNotLow(true)
                .setPeriodic(15*60*1000)
                .setPersisted(true)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int result=  scheduler.schedule(jobInfo);
        if(result == JobScheduler.RESULT_SUCCESS)
            Log.d("12345", "scheduleJob: " + "created");
        else
            Log.d("12345", "scheduleJob: " + "not created");
    }


    private void requestUsageStatsPermissions() {
        if (!hasUsageAccessPermission())
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
    }

    private boolean hasUsageAccessPermission() {
        AppOpsManager opsManager = (AppOpsManager) getApplicationContext().getSystemService(APP_OPS_SERVICE);
        int mode = opsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        Log.d("12345", "hasUsageAccessPermission: " + mode);
        return mode == AppOpsManager.MODE_ALLOWED;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        scheduleJob();
    }
}