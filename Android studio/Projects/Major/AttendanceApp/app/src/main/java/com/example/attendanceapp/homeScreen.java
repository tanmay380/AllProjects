package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class homeScreen extends AppCompatActivity {
    Button scan;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        sharedPreferences = getSharedPreferences("Information",MODE_PRIVATE);

        StudentProfile.rollno=sharedPreferences.getString("Roll", null);

        scan= findViewById(R.id.button);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ScannerView.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(),StudentProfile.class));
//                Toast.makeText(getApplicationContext(), "sdhf", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myattend:
                startActivity(new Intent(getApplicationContext(), myAttendance.class));
                break;
            case R.id.logout:

                SharedPreferences.Editor editor  = sharedPreferences.edit();
                editor.putString("logged",null);
                editor.apply();

                finishAndRemoveTask();
        }


        return true;
    }
}