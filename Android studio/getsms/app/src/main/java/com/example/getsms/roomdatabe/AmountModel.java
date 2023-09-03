package com.example.getsms.roomdatabe;

import androidx.annotation.NonNull;

public class AmountModel {
    int Amount, PaidAmount;

    public AmountModel(int Amount, int b) {
        this.Amount = Amount;
        this.PaidAmount = b;
    }

    public AmountModel() {
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        this.Amount = amount;
    }

    public int getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.PaidAmount = paidAmount;
    }

    @NonNull
    @Override
    public String toString() {
        return Amount + "  " + PaidAmount;
    }
}
