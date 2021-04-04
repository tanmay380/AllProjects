package com.example.steps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView step;
        SensorManager sManager;
    Sensor stepSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         step= findViewById(R.id.steps);
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
         stepSensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

    }
    private long steps=0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor= event.sensor;
        float[] values= event.values;
        View v= findViewById(R.id.steps);

        int value=-1;

        if (values.length>0){
            value= (int) values[0];
        }
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            steps++;
        }
        //Toast.makeText(getApplicationContext(), String.valueOf( steps), Toast.LENGTH_LONG).show();
        step.setText(String.valueOf( steps));
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sManager.unregisterListener(this, stepSensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }
}