package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp.retrofit.*;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    EditText emailedt,passwordedt,mobileedt;
    Button regSignUp;
    TextView tvreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailedt = findViewById(R.id.reg_email);
        passwordedt = findViewById(R.id.reg_password);
        mobileedt = findViewById(R.id.reg_mobile);
        regSignUp = findViewById(R.id.signup);
        tvreport = findViewById(R.id.report_signup);

        regSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister(emailedt.getText().toString().trim(), passwordedt.getText().toString().trim(),
                        mobileedt.getText().toString().trim());
            }
        });

    }

    private void userRegister(String email , String password, String mobile)
    {
        String name="not Applicable";
        String address ="not Applicable";

        Call<signUp_response_model> call = ApiController.getInstance()
                                                .getapi()
                                                .getregister(name,email,password,mobile,address);

        call.enqueue(new Callback<signUp_response_model>() {
            @Override
            public void onResponse(Call<signUp_response_model> call, Response<signUp_response_model> response) {
                signUp_response_model obj =response.body();
                Log.d("1234", "onResponse: " +response.isSuccessful());
                Log.d("1234", "onResponse: " +response.errorBody());
                Log.d("1234", "onResponse: " +response.body().toString());
                String result1=obj.getMessage();
                Log.d("1234", "onResponse: " +result1);

                if (result1.equals("inserted")) {
                    tvreport.setText("Successfully Registered");
                    emailedt.setText("");
                    passwordedt.setText("");
                    mobileedt.setText("");
                }else if (result1.equals("exist")) {
                    tvreport.setText("Already Registed");
                    emailedt.setText("");
                    passwordedt.setText("");
                    mobileedt.setText("");
                }
            }

            @Override
            public void onFailure(Call<signUp_response_model> call, Throwable t) {
                Log.d("1234", "onFailure: " +t);
                tvreport.setText("SOmethign went Wrong");
                tvreport.setTextColor(Color.RED);
            }
        });
    }
}