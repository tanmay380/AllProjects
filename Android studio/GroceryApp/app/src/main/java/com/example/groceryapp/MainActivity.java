package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button joinnowbutton, loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        joinnowbutton= findViewById(R.id.main_join_now_btn);
        loginbutton= findViewById(R.id.main_loginbtn);

        loginbutton.setOnClickListener(v -> {
            Intent intent= new Intent(MainActivity.this, loginActivity.class);
            startActivity(intent);
        });

        joinnowbutton.setOnClickListener(v -> {
                Intent intent= new Intent(MainActivity.this, Register_Activity.class);
                startActivity(intent);
            });

    }
}
