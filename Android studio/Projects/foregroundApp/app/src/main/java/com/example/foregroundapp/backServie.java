package com.example.foregroundapp;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rvalerio.fgchecker.AppChecker;

import java.util.Arrays;
import java.util.List;

public class backServie extends JobService {
    private static final String TAG = "12345";
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "onStartJob: " + "JOb Started");
        dobackgroundWord(jobParameters);
        return true;
    }

    private void dobackgroundWord(JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String[] arr = {"com.whatsapp", "com.netflix.mediaclient"};
                List<String> list = Arrays.asList(arr);

                AppChecker appChecker = new AppChecker();
                appChecker.whenAny(new AppChecker.Listener() {
                    @Override
                    public void onForeground(String process) {
                        if (list.contains(process)) {
                            Log.d("12345", "onForeground: " + "succes");
                        }
                    }
                })
                        .timeout(1000)
                        .start(getApplicationContext());
            }
        }).start();
        Log.d(TAG, "dobackgroundWord: "+"job finished");
        jobFinished(jobParameters, true);
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob: " + "stopped");
        return true;
    }
}
