package com.example.dbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);


    new bgthred().start();
    }
    class bgthred extends  Thread{
        @Override
        public void run() {
            super.run();
            try {
                DataInputStream dataInputStream = new DataInputStream(getAssets().open(String.format("words.txt")));
                Database database = new Database(getApplicationContext());



                        database.addWords(dataInputStream);

//                database.getcont("yec");
//                database.deleteAll();
//                database.a(dataInputStream);
                Log.d("12345", "onCreate: " + "databse done");

            } catch (IOException e) {
                Log.d("12345", "cathc: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}