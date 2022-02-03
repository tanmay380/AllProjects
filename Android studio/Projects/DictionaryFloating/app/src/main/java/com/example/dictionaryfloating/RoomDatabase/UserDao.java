package com.example.dictionaryfloating.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Insert into `table` (Appname) Values (:name) ")
    void insert(String name);

    @Query("Delete from `table` where Appname=(:name) ")
    void delete(String name);

    @Query("Select Count(AppName) from `table` where AppName=(:name)")
    int exits(String name);

    @Query("Select * from `table`")
    List<User> selectedApps();

}
