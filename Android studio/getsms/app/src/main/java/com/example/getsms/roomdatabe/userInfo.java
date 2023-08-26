package com.example.getsms.roomdatabe;

import android.util.Pair;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class userInfo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "amount")
    public String amt;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "userInvolved")
    public ArrayList<String> UsersInvolved;

    public userInfo(String amt, String date, ArrayList<String> usersInvolved) {
        this.amt = amt;
        this.date = date;
        UsersInvolved = usersInvolved;
    }

    public userInfo() {

    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getUsersInvolved() {
        return UsersInvolved;
    }

    public void setUsersInvolved(ArrayList<String> usersInvolved) {
        UsersInvolved = usersInvolved;
    }
}
