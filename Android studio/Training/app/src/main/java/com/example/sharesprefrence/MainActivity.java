package com.example.sharesprefrence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);
        Button btn = findViewById(R.id.btn);

        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);
        Log.d("12345", "onCreate: " + sp.getAll());
        if(sp.contains("btn")){
            Toast.makeText(getApplicationContext(), "in", Toast.LENGTH_SHORT).show();
            tv.setText(sp.getString("btn", null));
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv.setText("change Text");
                SharedPreferences sharedPreferences = getSharedPreferences("App", MODE_PRIVATE);
                sharedPreferences.edit().putString("btn", tv.getText().toString());
                sharedPreferences.edit().apply();
                Log.d("12345", "onClick: " + sharedPreferences.getAll());
            }
        });
    }
}