package com.example.attendanceapp.retrofit;

public class get_subjects {
    String sname;
    int s_id;

    public get_subjects(String sname, int s_id) {
        this.sname = sname;
        this.s_id = s_id;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String get_sub_name() {
        return sname;
    }

    public get_subjects() {
    }
}
