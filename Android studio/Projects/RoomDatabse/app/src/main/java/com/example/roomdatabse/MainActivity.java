package com.example.roomdatabse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new  bgThread().start();

    }

     class bgThread extends Thread {
         @Override
         public void run() {
             super.run();
             AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                     AppDatabase.class, "User_Table3").fallbackToDestructiveMigration().build();

             UserDao dao= db.userDao();
             dao.insert(new User(1, "tanmay", "singhal"));
//             dao.insert(new User(2, "tanmay", "singhal"));
//             dao.delete();
         }


    }
}