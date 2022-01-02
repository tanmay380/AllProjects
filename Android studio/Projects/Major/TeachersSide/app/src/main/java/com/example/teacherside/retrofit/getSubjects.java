package com.example.teacherside.retrofit;

public class getSubjects {
    int s_id;
    String sname;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public getSubjects(int s_id, String sname) {
        this.s_id = s_id;
        this.sname = sname;
    }

    @Override
    public String toString(){
        return sname;
    }
}
