package com.example.roomdatabse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "User Table").build();

        UserDao dao= db.userDao();
        dao.insert(new User(1, "tanmay", "singhal"));

    }
}