package com.example.teacherside;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teacherside.retrofit.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFaculty extends AppCompatActivity {
    EditText username, password;
    Button login;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_faculty);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.button);
        sp = getSharedPreferences("loggedIn", MODE_PRIVATE);
        if(sp.contains("loggedIn")){
            startActivity(new Intent(LoginFaculty.this, MainActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFaculty(username.getText().toString().trim(), password.getText().toString().trim());
            }
        });

    }

    private void loginFaculty(String username, String password) {
        Call<facultylogin> call = apicontroller.getInstance()
                .getapi()
                .loginfaculty(username, password);
        call.enqueue(new Callback<facultylogin>() {
            @Override
            public void onResponse(Call<facultylogin> call, Response<facultylogin> response) {
                facultylogin msg = response.body();
                String message = msg.getMessage();
                Log.d("12345", "onResponse: " + message);
                if (!message.equals("Wrong id or password")) {
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("loggedIn", true);
                    editor.putString("fid",message);
                    editor.apply();
                    startActivity(new Intent(LoginFaculty.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<facultylogin> call, Throwable t) {
                Log.d("12345", "onFailure: " + t.getMessage());
            }
        });
    }
}