package com.example.dictionaryfloating;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dictionaryfloating.RoomDatabase.AppDatabase;
import com.example.dictionaryfloating.RoomDatabase.User;
import com.example.dictionaryfloating.RoomDatabase.UserDao;
import com.example.dictionaryfloating.backgroundservice.CheckFGApp;
import com.example.dictionaryfloating.floatingpart.FloatingIcon;
import com.example.dictionaryfloating.recyclerView.Adapter;
import com.example.dictionaryfloating.recyclerView.ModelClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    HashMap<String, String> apps;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServiceRunning()){
            Toast.makeText(getApplicationContext(), "Running", Toast.LENGTH_SHORT).show();
            stopService(new Intent(this, FloatingIcon.class));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, CheckFGApp.class);
        JobInfo jobInfo = new JobInfo.Builder(427462,componentName)
                .setRequiresBatteryNotLow(true)
                .setPersisted(true)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int result= scheduler.schedule(jobInfo);
        if(result==JobScheduler.RESULT_SUCCESS){
            Log.d("12345", "scheduleJob:  " + "succes");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            scheduleJob();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isServiceRunning()){
            Toast.makeText(getApplicationContext(), "Running", Toast.LENGTH_SHORT).show();
            stopService(new Intent(this, FloatingIcon.class));
        }
        stopService(new Intent(MainActivity.this, CheckFGApp.class));
    }

    public void getAllApps() {
        requestUsageStatsPermission();
        if (!checkPermissionexists()) {
            requestOverlayPermission();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.dialoguebox, null);
//        getApps(view);
            ProgressBar progressBar = view.findViewById(R.id.progress_app);
            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler(view.getContext().getMainLooper());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    getApps(view, progressBar);
                }
            };
            handler.post(runnable);
            builder.setView(view);
            builder.setCancelable(true);
            builder.setTitle("SELECT THE APPS");
            builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            })
                    .create().show();
        }

    }

    public void getApps(View v, ProgressBar progressBar) {
        Log.d("12345", "created: ");

        RecyclerView recyclerView = v.findViewById(R.id.rcv);

        Adapter adapter;
        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        String[] arr = new String[packList.size()];

        apps = new HashMap<>();
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            String packagename = packInfo.applicationInfo.packageName;
            String label = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();
            apps.put(label, packagename);
            arr[i] = label;
        }

        Arrays.sort(arr);
        for (String ar : arr) {
            arrayList.add(new ModelClass(ar, false));
        }

        adapter = new Adapter(arrayList, getApplicationContext(),apps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        progressBar.setVisibility(View.GONE);
    }

    public boolean isServiceRunning(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for(ActivityManager.RunningServiceInfo manager:activityManager.getRunningServices(Integer.MAX_VALUE)){
            if(FloatingIcon.class.getName().equals(manager.service.getClassName()))
                return true;
        }
        return false;
    }

    public void requestOverlayPermission() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true)
                .setTitle("Screen OverLay Permissions")
                .setMessage("In order to display over your required apps, we need this permission to do")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, RESULT_OK);
                    }
                }).show();
    }

    public void requestUsageStatsPermission() {
     if (!hasAccessUsageStats())
         startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
    }

    public boolean hasAccessUsageStats() {
        AppOpsManager opsManager = (AppOpsManager) getApplicationContext().getSystemService(APP_OPS_SERVICE);
        int mode = opsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        return  mode == AppOpsManager.MODE_ALLOWED;
    }

    public boolean checkPermissionexists() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
            return Settings.canDrawOverlays(this);
        else
            return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getApps:
                getAllApps();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}