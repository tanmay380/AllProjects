package com.example.dictionaryfloating.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Apiset {

    @GET("api/v2/entries/en/{word}")
    Call<List<BaseClass>> results(@Path("word") String word);
}
