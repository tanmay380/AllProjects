package com.example.attendanceapp;

import com.example.attendanceapp.retrofit.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class myAttendance extends AppCompatActivity {
    RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attendance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcv=findViewById(R.id.recview);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        Toast.makeText(getApplicationContext(), StudentProfile.rollno, Toast.LENGTH_SHORT).show();

        get_Classes();
    }

    private void get_Classes() {
        Call<List<get_subjects>> subjects= apicontroller.getInstance()
                .getapi()
                .getsubject(StudentProfile.rollno);
        subjects.enqueue(new Callback<List<get_subjects>>() {
            @Override
            public void onResponse(Call<List<get_subjects>> call, Response<List<get_subjects>> response) {
                Log.d("12345", "onresponse: " + " ");
                List<get_subjects> sbject= response.body();
                myadaper adpa= new myadaper(sbject);
                rcv.setAdapter(adpa);

            }

            @Override
            public void onFailure(Call<List<get_subjects>> call, Throwable t) {
                Log.d("12345", "onfalire: " + t.getMessage());
            }
        });
    }
}