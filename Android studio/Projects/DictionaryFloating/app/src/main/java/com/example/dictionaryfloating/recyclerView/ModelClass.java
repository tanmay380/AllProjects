package com.example.dictionaryfloating.recyclerView;

import android.graphics.drawable.Drawable;

public class ModelClass {
    String appname="";
    boolean checked  = false ;
    Drawable resource;

    public ModelClass(Drawable resource,String appname, boolean checked) {
        this.appname = appname;
        this.checked = checked;
        this.resource= resource;
    }

    public Drawable getResource() {
        return resource;
    }

    public void setResource(Drawable resource) {
        this.resource = resource;
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
