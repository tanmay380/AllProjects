package com.example.dictionaryfloating.backgroundservice;

import android.app.ActivityManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.dictionaryfloating.MainActivity;
import com.example.dictionaryfloating.floatingpart.FloatingIcon;
import com.example.dictionaryfloating.floatingpart.HoldingBox;
import com.example.dictionaryfloating.recyclerView.Adapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rvalerio.fgchecker.AppChecker;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckFGApp extends JobService {
    ArrayList<String> appPackage = new ArrayList<>();
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        doInBackground(jobParameters);
        return true;
    }

    private void doInBackground(JobParameters jobParameters) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                AppChecker app = new AppChecker();
                app.whenAny(new AppChecker.Listener() {
                    @Override
                    public void onForeground(String process) {
                        new bgThread().start();
                        if(appPackage.contains(process) && !process.equals(getPackageName())){
                            if(!isrunning()) {
                                startService(new Intent(CheckFGApp.this, FloatingIcon.class));

                            }
                        }else{
                            stopService(new Intent(CheckFGApp.this, FloatingIcon.class));
                        }
                    }
                }).timeout(1000)
                        .start(getApplicationContext());
            }
        }).start();
        jobFinished(jobParameters, true);
    }
    private boolean isrunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo info : activityManager.getRunningServices(Integer.MAX_VALUE))
            if(FloatingIcon.class.getName().equals(info.service.getClassName()) || HoldingBox.class.getName().equals(info.service.getClassName()))
                return true;
        return  false;
    }
    class bgThread extends Thread{
        @Override
        public void run() {
            super.run();

            SharedPreferences sp = getSharedPreferences("AppPackageName",0);
            Gson gson = new Gson();
            String json = sp.getString("appPackage",null);
            Type type = new TypeToken<ArrayList<String>>() {}.getType();

            appPackage = gson.fromJson(json,type);

            if(appPackage==null){
                appPackage=new ArrayList<>();
            }
        }
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
