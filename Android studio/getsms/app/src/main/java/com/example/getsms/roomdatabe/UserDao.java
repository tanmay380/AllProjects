package com.example.getsms.roomdatabe;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.getsms.Model;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(userInfo info);

    @Query("Select * from userInfo")
    List<userInfo> selectAll();
}
