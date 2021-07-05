package com.example.a10codingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class secondscreen extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondscreen);

        Intent intent= getIntent();
        textView= findViewById(R.id.viewintent);
        textView.setText(intent.getStringExtra("msg"));

    }
}