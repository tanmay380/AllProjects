package com.example.widhets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        RemoteViews views = new RemoteViews(getPackageName(),R.layout.examplewidget);


    }
}