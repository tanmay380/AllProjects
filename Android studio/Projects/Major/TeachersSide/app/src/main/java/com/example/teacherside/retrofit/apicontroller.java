package com.example.teacherside.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {
    static final String url = "http://192.168.1.5/Attendance/";
    private static apicontroller apicontroller;
    private static Retrofit retrofit;

    apicontroller(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static  synchronized apicontroller getInstance(){
        if (apicontroller==null)
            apicontroller = new apicontroller();
        return apicontroller;
    }

    public apiset getapi(){
        return retrofit.create(apiset.class);
    }
}
