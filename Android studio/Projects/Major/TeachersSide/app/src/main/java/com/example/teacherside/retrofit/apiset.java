package com.example.teacherside.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface apiset {

    @GET("faculty/get_Subjects.php")
    Call<List<getSubjects>> getSubjects(
            @Query("f_id") String fid
    );


    @FormUrlEncoded
    @POST("faculty/faculty_login.php")
    Call<facultylogin> loginfaculty(
           @Field("f_name") String fname,
           @Field("pass") String pass
    );


}
