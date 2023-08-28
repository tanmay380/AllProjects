package com.example.getsms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.UserDao;
import com.example.getsms.roomdatabe.AmountInfo;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final Boolean DEMO = false;

    RecyclerView recyclerView;
    List<AmountInfo> list;
    public static RecyclerView.Adapter adapter1;
    private AppDatabase db;
    private int REQUEST_CODE = 1;

    String[] permission = {Manifest.permission.RECEIVE_SMS,
            Manifest.permission.POST_NOTIFICATIONS};

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermissions(MainActivity.this,permission)) {
            ActivityCompat.requestPermissions(MainActivity.this,permission,1);
        }

        recyclerView = findViewById(R.id.rcv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        registerReceiver(broadcastReceiver, new IntentFilter("MESSAGE_RECIEVED_UPDATE"));

    }


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // internet lost alert dialog method call from here...
            Log.d("12345", "onReceive: mainactivirt");
            getList();
        }
    };


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

            for (String permission: PERMISSIONS){

                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {
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
            }else {
                Toast.makeText(this, "SMS Permission is denied", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification Permission is granted", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Notification Permission is denied", Toast.LENGTH_SHORT).show();
            }



        }
    }


    public void getList() {
        Log.d("12345", "getList: called");
        if (!DEMO) {
            Log.d("12345", "getList: called" + db);
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "Data_Store").allowMainThreadQueries().build();

            UserDao dao = db.userDao();
            list = dao.selectAll();
            Collections.reverse(list);
            FragmentManager fragmentManager = getSupportFragmentManager();
            adapter1 = new Adapter(list, fragmentManager);
            recyclerView.setAdapter(adapter1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}