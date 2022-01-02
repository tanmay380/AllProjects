package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.attendanceapp.retrofit.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class faculty extends AppCompatActivity {
    String roll;
    RecyclerView recv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        recv=findViewById(R.id.rcv);
        recv.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        roll = intent.getStringExtra("roll");
        Toast.makeText(getApplicationContext(), roll, Toast.LENGTH_SHORT).show();

        retrofitCall();

    }

    private void retrofitCall() {
        Call<List<getFaculty>> call = apicontroller.getInstance()
                .getapi().get_faculty(roll);
        call.enqueue(new Callback<List<getFaculty>>() {
            @Override
            public void onResponse(Call<List<getFaculty>> call, Response<List<getFaculty>> response) {
                List<getFaculty> fac= response.body();
                Log.d("12345", "onResponse: " + fac.get(0).getSname() + "  "+ fac.get(0).getF_name());
                myadapterfaculty adpa= new myadapterfaculty(fac);
                recv.setAdapter(adpa);
            }

            @Override
            public void onFailure(Call<List<getFaculty>> call, Throwable t) {
                Log.d("12345", "onfailure: "+ t.getMessage());
            }
        });
    }
}