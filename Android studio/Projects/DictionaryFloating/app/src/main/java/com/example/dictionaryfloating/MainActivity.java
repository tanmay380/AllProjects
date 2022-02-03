package com.example.dictionaryfloating;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.ProgressDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dictionaryfloating.DataBase.DataBaseWord;
import com.example.dictionaryfloating.RoomDatabase.AppDatabase;
import com.example.dictionaryfloating.RoomDatabase.User;
import com.example.dictionaryfloating.RoomDatabase.UserDao;
import com.example.dictionaryfloating.backgroundservice.CheckFGApp;
import com.example.dictionaryfloating.floatingpart.FloatingIcon;
import com.example.dictionaryfloating.recyclerView.Adapter;
import com.example.dictionaryfloating.recyclerView.ModelClass;
import com.example.dictionaryfloating.recyclerView.SearchRCVAdapter;
import com.example.dictionaryfloating.retrofit.ApiController;
import com.example.dictionaryfloating.retrofit.BaseClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    public static ArrayList<String> appPackage;
    HashMap<String, String> apps;
    public static ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        apps = new HashMap<>();

        SharedPreferences sp = getSharedPreferences("AppPackageName", 0);
        Gson gson = new Gson();
        String json = sp.getString("appPackage", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        appPackage = new ArrayList<>();

        appPackage = gson.fromJson(json, type);

        if (appPackage == null) {
            appPackage = new ArrayList<>();
        }
        Log.d("12345", "run: " + Arrays.toString(appPackage.toArray()));
        if (isServiceRunning()) {
            Toast.makeText(getApplicationContext(), "Running", Toast.LENGTH_SHORT).show();
            stopService(new Intent(this, FloatingIcon.class));
        }

        SearchRCVAdapter rcvAdapter = new SearchRCVAdapter(getApplicationContext());
        rcvAdapter.retrofitCall("abandon");

        new words().start();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scheduleJob() {
        ComponentName componentName = new ComponentName(this, CheckFGApp.class);
        JobInfo jobInfo = new JobInfo.Builder(427462, componentName)
                .setRequiresBatteryNotLow(true)
                .setPersisted(true)
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS) {
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
        if (isServiceRunning()) {
            Toast.makeText(getApplicationContext(), "Running", Toast.LENGTH_SHORT).show();
            stopService(new Intent(this, FloatingIcon.class));
        }
        Boolean answer = stopService(new Intent(MainActivity.this, CheckFGApp.class));
        Log.d("12345", "onResume: " + answer);
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

        if (apps.isEmpty()) {
            for (int i = 0; i < packList.size(); i++) {
                PackageInfo packInfo = packList.get(i);
                String packagename = packInfo.applicationInfo.packageName;
                String label = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                Log.d("12345", "getApps: " + label + " " + packagename);
                apps.put(label, packagename);
                arr[i] = label;
            }

            Arrays.sort(arr);
            int i = 0;
            for (String ar : arr) {
                Drawable icon = null;
                try {
                    icon = getApplicationContext().getPackageManager().getApplicationIcon(apps.get(ar));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                arrayList.add(new ModelClass(icon, ar, false));
                i++;
            }
        }

        adapter = new Adapter(arrayList, getApplicationContext(), apps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        progressBar.setVisibility(View.GONE);
    }

    public boolean isServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo manager : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (FloatingIcon.class.getName().equals(manager.service.getClassName()))
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
        if (!hasAccessUsageStats()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Usage Access Permission")
                    .setMessage("In order to show floating icon on app, this persmission is required")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Log.d("12345", "onClick: " + " on");

                            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onBackPressed();
                        }
                    }).show();
        }

    }

    public boolean hasAccessUsageStats() {
        AppOpsManager opsManager = (AppOpsManager) getApplicationContext().getSystemService(APP_OPS_SERVICE);
        int mode = opsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
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

        MenuItem item = menu.findItem(R.id.searchWord);
        RecyclerView recyclerView = findViewById(R.id.rcv);

        SearchRCVAdapter adapter = new SearchRCVAdapter(getApplicationContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.setScrollBarSize(10);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.clearFocus();
        searchView.setIconified(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }

    class words extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                DataBaseWord dataBaseWord = new DataBaseWord(getApplicationContext());
                Cursor cursor = dataBaseWord.getReadableDatabase().rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                        + "Word" + "'", null);
                if (cursor.getCount() == 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.setTitle("Optimising Data");
                            progressDialog.setCancelable(false);
                            progressDialog.setProgress(0);
                            progressDialog.setMax(100);
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressDialog.show();
                        }
                    });

                    DataInputStream dataInputStream;

                    dataInputStream = new DataInputStream(getAssets().open(String.format("words1.sql")));
                    dataBaseWord.addWords(dataInputStream);
                    progressDialog.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getApps:
                getAllApps();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

}