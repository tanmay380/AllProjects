package com.example.dictionaryfloating.recyclerView;

public class ModelClass {
    String appname="";
    boolean checked  = false ;

    public ModelClass(String appname, boolean checked) {
        this.appname = appname;
        this.checked = checked;
    }

    public String getAppname() {
        return appname;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
