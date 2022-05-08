package com.example.getsms.roomdatabe;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {userInfo.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
