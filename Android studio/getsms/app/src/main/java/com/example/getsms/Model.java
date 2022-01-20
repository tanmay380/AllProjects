package com.example.getsms;

import java.time.LocalDateTime;

public class Model {
    String sms;
    String time;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Model() {
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public Model(String sms, String time) {
        this.sms = sms;
        this.time= time;
    }
}
