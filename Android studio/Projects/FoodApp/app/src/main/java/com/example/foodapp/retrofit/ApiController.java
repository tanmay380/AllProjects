package com.example.foodapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    public static final String url = "http://192.168.1.7/ecomapi/";
    private static ApiController clientobject;
    private static Retrofit retrofit;

    ApiController()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance() {
        if (clientobject == null)
            clientobject = new ApiController();

        return clientobject;
    }

    public ApiSet getapi()
    {
       return retrofit.create(ApiSet.class);
    }


}
