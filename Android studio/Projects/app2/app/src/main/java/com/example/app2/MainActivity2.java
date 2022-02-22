package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.next);
        button.setOnClickListener(View-> {
            Intent intent = new Intent();
            intent.setAction("com.example.app1.MainActivity");
            startActivity(intent);
        });
    }
}