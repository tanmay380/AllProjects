package com.example.elderlyhelperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button setAlaram, colorchange;
    final ArrayList<AlarmView> talarm= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmViewAdaptor alarmViewAdaptor= new AlarmViewAdaptor(this, talarm);
        ListView listView= findViewById(R.id.listview);
        listView.setAdapter(alarmViewAdaptor);

        timePicker = findViewById(R.id.hour);
        setAlaram = findViewById(R.id.alarm);

        setAlaram.setOnClickListener(v -> {
            int hour, min;
            hour = timePicker.getHour();
            min = timePicker.getMinute();
            String time= (hour +":"+ min);
            talarm.add(new AlarmView(time));
            alarmViewAdaptor.notifyDataSetChanged();

            ArrayList<Integer> alarmDays = new ArrayList<>();


            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
            intent.putExtra(AlarmClock.EXTRA_MINUTES, min);
            intent.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
            intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            if (hour <= 24 && min <= 60)
                startActivity(intent);
        });

    }
}