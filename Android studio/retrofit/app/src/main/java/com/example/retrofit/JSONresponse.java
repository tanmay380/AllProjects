package com.example.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONresponse {

    @SerializedName("states")
    @Expose
    private states[] statesA;

    public states[] getStatesArray() {
        return statesA;
    }

    public void setStatesArray(states[] statesArray) {
        this.statesA = statesArray;
    }
}
