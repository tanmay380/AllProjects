package com.example.elderlyhelperapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class runnew extends AppCompatActivity {

    TimePicker timePicker;
    Button setAlaram, colorchange;
    final ArrayList<AlarmView> talarm = new ArrayList<>();

    boolean clicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        colorchange= findViewById(R.id.button);
        colorchange.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (clicked) {
                    clicked = false;
                    colorchange.setBackgroundColor(Color.blue(255));
                    System.out.println("COLOR BACKGROUD" + colorchange.getBackground());
                }else
                {
                    System.out.println("CLICKED IN ELSE");
                    clicked=true;
                    colorchange.setBackgroundColor(R.color.black);
                }
            }
        });
    }
}