package com.example.dictionaryfloating;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        tv = findViewById(R.id.totalapp);


        UsageStatsManager us = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (us.getAppStandbyBucket()){

            }
        } {
            Log.d("12345", "onCreate: " + "  ");
        }

    }


    public void getAllApps(View view) {

        List<PackageInfo> packList = getPackageManager().getInstalledPackages(0);
        Toast.makeText(getApplicationContext(), packList.size() + " ", Toast.LENGTH_SHORT).show();

        String[] apps = new String[packList.size()];
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            apps[i] = packInfo.applicationInfo.loadLabel(getPackageManager()).toString();

            Log.d("12345", "getAllApps: " + i + " " + apps[i]);
        }
        Arrays.sort(apps);
        Toast.makeText(getApplicationContext(), apps.length + " ", Toast.LENGTH_SHORT).show();

        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, apps));

//        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
//        startActivity(intent);


    }
}