package com.example.attendanceapp.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("signup.php")
    Call<signup_response_model> getregister(
            @Field("roll_number") String rno,
            @Field("name") String name,
            @Field("c_id") int class_id
    );

    @GET("getclass.php")
    Call<List<getClassname>> getclassname();

    @FormUrlEncoded
    @POST("getsubject.php")
    Call<List<get_subjects>> getsubject(
            @Field("roll_number") String roll
    );
}


