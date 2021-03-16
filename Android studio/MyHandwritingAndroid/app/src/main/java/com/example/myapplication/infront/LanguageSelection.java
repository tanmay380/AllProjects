package com.example.myapplication.infront;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import java.util.Locale;

public class LanguageSelection extends AppCompatActivity {
    public Button english,number;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_first);

        english=findViewById(R.id.english);
        number= findViewById(R.id.number);
    }

    public void langueageselect(View v){
        Intent intent=null;
        switch (v.getId()) {
            case R.id.english:
                setLocale("en");
                intent = new Intent(this, CharacterSelection.class);
                break;
            case R.id.number:
                setLocale("hi");
                intent = new Intent(this, CharacterSelection.class);
                break;
            case R.id.hindi:
                setLocale("bn");
                intent = new Intent(this, CharacterSelection.class);
                break;
        }
        startActivity(intent);


    }


    public void setLocale(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SplashScreen.CHARACTER_LIST = getResources().getStringArray(R.array.Characters);
    }


}

