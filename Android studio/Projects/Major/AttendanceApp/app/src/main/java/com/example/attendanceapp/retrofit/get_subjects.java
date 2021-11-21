package com.example.attendanceapp.retrofit;

public class get_subjects {
    String sname;

    public String get_sub_name() {
        return sname;
    }

    public void setClass_Name(String class_Name) {
        this.sname = class_Name;
    }

    public get_subjects(String class_Name) {
        this.sname = class_Name;
    }

    public get_subjects() {
    }
}
