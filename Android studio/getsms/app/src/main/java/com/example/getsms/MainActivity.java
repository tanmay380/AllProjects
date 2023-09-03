package com.example.getsms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.getsms.Fragments.FriendsFragment;
import com.example.getsms.Fragments.HomeFragment;
import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.AmountInfo;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final Boolean DEMO = false;

    RecyclerView recyclerView;
    List<AmountInfo> list;
    public static RecyclerView.Adapter adapter1;
    private AppDatabase db;
    private int REQUEST_CODE = 1;

    String[] permission = {Manifest.permission.RECEIVE_SMS,
            Manifest.permission.POST_NOTIFICATIONS};

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;


    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = findViewById(R.id.drawerLayour);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (!hasPermissions(MainActivity.this, permission)) {
            ActivityCompat.requestPermissions(MainActivity.this, permission, 1);
        }

        if (savedInstanceState == null) {
            Log.d("12345", "onCreate: saed insraance null");
            HomeFragment homeFragment = HomeFragment.newInstance(getIntent().getStringExtra("notification"));
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
            navView.setCheckedItem(R.id.freinds_menu);
        }
    }

//    private void getPersmissions() {
//        Log.d("12345", "getPersmissions: " + ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.POST_NOTIFICATIONS));
//        if (ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                requestStoragePermission();
//            }
//        }
//    }
//
//    private void requestStoragePermission() {
////        for (int i = 0; i < permission.length; i++) {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS)) {
//            new AlertDialog.Builder(this)
//                    .setTitle("Permission Needed")
//                    .setMessage("This permission to access your messages")
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE);
//
//                        }
//                    })
//                    .setNegativeButton("Cancel", (dialog, which) -> {
//                        dialog.dismiss();
//                    }).create()
//                    .show();
//
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE);
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.d("12345", "onRequestPermissionsResult: " + Arrays.toString(grantResults));
//                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
////                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},REQUEST_CODE);
////                finishAndRemoveTask();
//            }
//        }
//    }

    private boolean hasPermissions(Context context, String... PERMISSIONS) {
        if (context != null && PERMISSIONS != null) {
            for (String permission : PERMISSIONS) {

                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "SMS Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification Permission is granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification Permission is denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_activity_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                break;
            case R.id.freinds_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FriendsFragment()).commit();
                break;
            case R.id.settings_menu:
                break;
        }

        drawerLayout.closeDrawers();
        return true;
    }

    /* todo: create a navigation menu which will show all the users that are included in transaction.
        and settings to show notification or not when message is recieved.
     */
}