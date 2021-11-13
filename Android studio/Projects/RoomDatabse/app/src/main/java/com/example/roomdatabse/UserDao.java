package com.example.roomdatabse;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

}
