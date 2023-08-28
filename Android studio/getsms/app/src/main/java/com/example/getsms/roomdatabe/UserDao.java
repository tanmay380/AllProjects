package com.example.getsms.roomdatabe;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(AmountInfo info);
    @Query("Select * from AmountInfo")
    List<AmountInfo> selectAll();

    @Query("Update amountinfo set splitEqually=:value where Tid=:id")
    void splitValueUpdate(int value, int id);

    @Query("Select * from AmountInfo where Tid =:id")
    AmountInfo getAmountInfo(int id);

//    @Query("")

    @Insert
    void insertUsers(UserInfo info);

    @Query("Delete from UserInfo where Tid=:id and date=:date")
    void deleteUsers(int id, String date);

    @Query("Select * from UserInfo where TiD=:id and date=:date")
    List<UserInfo> getUserInfo(int id, String date);


}
