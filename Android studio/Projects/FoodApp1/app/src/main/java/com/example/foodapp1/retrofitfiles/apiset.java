package com.example.foodapp1.retrofitfiles;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("signUp.php")
    Call<signp_response_model> getregister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<signin_response_model> loginuser(
            @Field("email") String email,
            @Field("password") String pass
    );


}
