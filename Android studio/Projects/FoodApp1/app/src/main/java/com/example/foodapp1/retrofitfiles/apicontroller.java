package com.example.foodapp1.retrofitfiles;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {
    static final String url = "http://192.168.1.7/ecomdb/";
    private static apicontroller clienobject;
    private static Retrofit retrofit;

    apicontroller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized apicontroller getInstance(){
        if (clienobject==null){
            clienobject=new apicontroller();
        }
        return clienobject;
    }

    public apiset getapi(){
        return retrofit.create(apiset.class);
    }
}
