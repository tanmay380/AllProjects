package com.example.gfgcovidvaccine;

import android.media.Image;

public class Item_class {


    String centerName;
    String centerlocation;
    String centerFromTime;
    String centerToTime;
    String fee;


    String ageLimit;
    String vaccineName;
    int availability;
    Image notfound;

    public Item_class(Image image){

    }

    public Item_class(String centerName, String centerlocation, String centerFromTime,
                      String centerToTime, String fee, String ageLimit, String vaccineName, int availability) {
        this.centerName = centerName;
        this.centerlocation = centerlocation;
        this.centerFromTime = centerFromTime;
        this.centerToTime = centerToTime;
        this.fee = fee;
        this.ageLimit = ageLimit;
        this.vaccineName = vaccineName;
        this.availability = availability;
    }

}
