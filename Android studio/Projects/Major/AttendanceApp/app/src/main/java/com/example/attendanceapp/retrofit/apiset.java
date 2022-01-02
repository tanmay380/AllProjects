package com.example.attendanceapp.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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


    @GET("get_total_class.php")
    Call<List<get_class_count>> getclasscount(
           @Query("roll_no") String roll_no,
           @Query("sub_id") int sub_id
    );

    @FormUrlEncoded
    @POST("get_faculty.php")
    Call<List<getFaculty>> get_faculty(
            @Field("roll_number") String roll_number
    );

    @GET("markattendance.php")
    Call<markattendance> markAttendance(
            @Query("roll_number") String r_no,
            @Query("s_id") int s_id
    );


}


