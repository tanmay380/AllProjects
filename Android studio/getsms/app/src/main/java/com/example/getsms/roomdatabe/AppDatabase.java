package com.example.getsms.roomdatabe;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {AmountInfo.class, UserInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
