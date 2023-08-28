package com.example.getsms.roomdatabe;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int TiD;
    @ColumnInfo(name = "Date")
    private String date;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Amount")
    private int amount;
    @ColumnInfo(name = "Paid", defaultValue = "false")
    private boolean Paid;
    @ColumnInfo(defaultValue = "0")
    private int PaidAmount;

    public UserInfo(int TiD, String date, String name, int amount) {
        this.TiD = TiD;
        this.date = date;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTiD() {
        return TiD;
    }

    public void setTiD(int tiD) {
        TiD = tiD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
    }

    public int getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        PaidAmount = paidAmount;
    }
}
