package com.example.getsms.roomdatabe;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class IndividualUserInfo {
    @PrimaryKey(autoGenerate = true)
    public int uId;
    private String name;
    private int amountPaid, totalAmount;

    public IndividualUserInfo(String name, int amountPaid, int totalAmount) {
        this.name = name;
        this.amountPaid = amountPaid;
        this.totalAmount = totalAmount;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
