package com.example.attendanceapp.retrofit;

public class getFaculty {
    String f_name;
    String sname;

    public getFaculty( String f_name, String sname) {
        this.f_name = f_name;
        this.sname = sname;
    }


    public getFaculty() {
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
