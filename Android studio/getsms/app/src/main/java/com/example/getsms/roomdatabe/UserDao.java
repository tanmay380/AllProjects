package com.example.getsms.roomdatabe;

import androidx.room.Dao;
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

    @Insert
    void insert(IndividualUserInfo userInfo);

    @Query("SELECT SUM(Amount) as Amount, SUM(PaidAmount) as PaidAmount FROM UserInfo WHERE name = :name")
    AmountModel getAmounts(String name);

    @Query("SELECT COUNT(:name) FROM IndividualUserInfo WHERE name = :name")
    int getCount(String name);

    @Query("Select * from IndividualUserInfo where name=:name")
    List<IndividualUserInfo> getDetail(String name);


    /* TODO: 02-09-2023 create a database which will show all the users that are included in transaction
        anytime. It will show me how much they own me and total mount that they have paid to me.
    */

}
