package com.example.teacherside;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import com.example.teacherside.retrofit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    List<getSubjects> subjectArray;
    RecyclerView recv;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recv = findViewById(R.id.recv);

        recv.setLayoutManager(new LinearLayoutManager(this));
        showProgress();
        SharedPreferences sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
        getSubjectRetrofitCall(sp.getString("fid", "-1"));
    }


    private void showProgress() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Getting Classes");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void getSubjectRetrofitCall(String fid) {

        Log.d("12345", "onResponse: " + fid);
        Call<List<getSubjects>> call = apicontroller.getInstance()
                .getapi()
                .getSubjects(fid);
        call.enqueue(new Callback<List<getSubjects>>() {
            @Override
            public void onResponse(Call<List<getSubjects>> call, Response<List<getSubjects>> response) {
                List<getSubjects> subjects = response.body();
                subjectArray = new ArrayList<>();
                for (int i = 0; i < subjects.size(); i++) {
                    subjectArray.add(new getSubjects(subjects.get(i).getS_id(), subjects.get(i).getSname()));
                }
                adapter = new Adapter(subjectArray);
                recv.setAdapter(adapter);

                progressDialog.cancel();
            }

            @Override
            public void onFailure(Call<List<getSubjects>> call, Throwable t) {

                Log.d("12345", "onResponse: " + t.getMessage());
            }
        });
    }
}