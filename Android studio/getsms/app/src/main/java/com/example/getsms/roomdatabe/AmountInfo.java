package com.example.getsms.roomdatabe;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class AmountInfo {
    @PrimaryKey(autoGenerate = true)
    private int Tid;

    @ColumnInfo(name = "amount")
    private int amt;

    private String date;
    @ColumnInfo(defaultValue = "0")
    private int splitEqually;

    public AmountInfo(int amt, String date) {
        this.amt = amt;
        this.date = date;
    }

    public int getSplitEqually() {
        return splitEqually;
    }

    public void setSplitEqually(int splitEqually) {
        this.splitEqually = splitEqually;
    }

    public AmountInfo() {

    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public int getTid() {
        return Tid;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
