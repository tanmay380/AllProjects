package com.example.qrcreator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    Button button;
    EditText name,roll;
    public static TextView scantext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.addname);
        roll=findViewById(R.id.rollnumber);
        button = findViewById(R.id.scan);
        scantext = findViewById(R.id.view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), scannerView.class)
                        .putExtra("name", name.getText().toString()).putExtra("roll", roll.getText().toString()));
            }
        });
    }
}