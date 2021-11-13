package com.example.attendanceapp.retrofit;

public class getClassname {
    int c_id;
    String  cname;

    public getClassname(int c_id, String cname) {
        this.c_id = c_id;
        this.cname = cname;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getC_id() {
        return c_id;
    }

    public String getCname() {
        return cname;
    }
    @Override
    public String toString(){
        return cname;
    }
}
