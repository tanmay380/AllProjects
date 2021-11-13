package com.example.roomdatabse;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstn;

    @ColumnInfo(name = "last_name")
    public String last;

    public User(int id, String firstn, String last) {
        this.id = id;
        this.firstn = firstn;
        this.last = last;
    }
}
