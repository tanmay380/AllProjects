package com.example.getsms;

import java.time.LocalDateTime;

public class Model {
    String sms;
    String time;
    String vendor;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

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

    public Model(String sms, String time, String vendor) {
        this.sms = sms;
        this.time = time;
        this.vendor = vendor;
    }
}
