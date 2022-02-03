package com.example.dictionaryfloating.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static String url = "https://api.dictionaryapi.dev/";
    private static ApiController apiController;
    private static Retrofit retrofit;

    ApiController(){
        retrofit  = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance(){
        if (apiController==null)
            apiController =  new ApiController();
        return apiController;
    }

    public Apiset apiset(){
        return retrofit.create(Apiset.class);
    }



}
