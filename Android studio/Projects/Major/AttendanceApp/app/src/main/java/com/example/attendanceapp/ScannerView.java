package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.attendanceapp.retrofit.*;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerView extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    String roll_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        Intent intent = getIntent();
        roll_number = intent.getStringExtra("roll");

        scannerView.startCamera();

    }

    @Override
    public void handleResult(Result sub_id) {
        markAttendance(Integer.parseInt(sub_id.getText()));
        onBackPressed();
    }

    private void markAttendance(Integer text) {
        Call<markattendance> call = apicontroller.getInstance()
                .getapi()
                .markAttendance(roll_number, text);
        call.enqueue(new Callback<markattendance>() {
            @Override
            public void onResponse(Call<markattendance> call, Response<markattendance> response) {
                markattendance markattendance = response.body();
                assert markattendance != null;
                String status = markattendance.getMessage();
                if (status.equals("marked")) {
                    Toast.makeText(getApplicationContext(), "Attendance Marked", Toast.LENGTH_SHORT).show();

                } else if (status.equals("exists")) {
                    Toast.makeText(getApplicationContext(), "Attendance Marked Already", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<markattendance> call, Throwable t) {
                Log.d("12345", "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Something Failed... Ask Teacher to mark attendance manually", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}