package com.example.getsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.getsms.roomdatabe.AppDatabase;
import com.example.getsms.roomdatabe.UserDao;
import com.example.getsms.roomdatabe.userInfo;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<userInfo> list;
    public static RecyclerView.Adapter adapter1;
    private AppDatabase db;
    private int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPersmissions();

        recyclerView = findViewById(R.id.rcv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getList();

    }



    private void getPersmissions() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else{
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission to access your messages")
                    .setPositiveButton("Ok", (dialog, which) -> {
                        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},REQUEST_CODE);

                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {
                        dialog.dismiss();
                    }).create()
                    .show();

        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
//                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},REQUEST_CODE);
//                finishAndRemoveTask();
            }
        }
    }

    private void getList() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Data_Store").allowMainThreadQueries().build();
        UserDao dao = db.userDao();
        list = dao.selectAll();
        Collections.reverse(list);
        adapter1 = new Adapter(list);
        recyclerView.setAdapter(adapter1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }
}