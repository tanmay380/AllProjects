package com.example.foodapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodapp1.retrofitfiles.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        tv = findViewById(R.id.login_tv);
        email = findViewById(R.id.log_emid);
        password = findViewById(R.id.log_empas);
        login = findViewById(R.id.loginbutton);

        checkUserExistence();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                loginUser(emailid, pass);
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, register.class));
                finish();
            }
        });


    }

    private void checkUserExistence()
    {
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if (sp.contains("username")){
            startActivity(new Intent(MainActivity.this, HomePage.class));
            finish();
        }
    }

    private void loginUser(String emailid, String pass) {
        Call<signin_response_model> call = apicontroller.getInstance()
                .getapi()
                .loginuser(emailid, pass);
        call.enqueue(new Callback<signin_response_model>() {
            @Override
            public void onResponse(Call<signin_response_model> call, Response<signin_response_model> response) {
                signin_response_model obj = response.body();
                String res = obj.getMessage().trim();

                if (res.equals("logged in")) {
                    SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", emailid);
                    editor.putString("password", pass);
                    editor.commit();
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }
                if (res.equals("wrong password")) {
                    password.setError("Wrong Password");
                }

                if (res.equals("register kar bsdk")) {
                    email.setError("Email Id not registed");
                    password.setText("");
                }
            }

            @Override
            public void onFailure(Call<signin_response_model> call, Throwable t) {
                email.setError("Email Id not registered");
            }
        });

    }
}