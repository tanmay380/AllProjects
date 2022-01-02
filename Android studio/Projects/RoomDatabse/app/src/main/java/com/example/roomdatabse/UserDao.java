package com.example.roomdatabse;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.SkipQueryVerification;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);


}
