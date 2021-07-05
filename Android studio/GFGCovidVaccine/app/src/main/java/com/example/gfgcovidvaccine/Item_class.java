package com.example.gfgcovidvaccine;

public class Item_class {
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

    String centerName;
    String centerlocation;
    String centerFromTime;
    String centerToTime;
    String fee;
    String ageLimit;
    String vaccineName;
    int availability;


}
