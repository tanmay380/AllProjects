package com.example.attendanceapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendanceapp.retrofit.*;
import com.example.attendanceapp.retrofit.get_subjects;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class myadaper extends RecyclerView.Adapter<viewhodler> {
//    ArrayList<class_model_class> data;
    List<get_subjects> data;

    public myadaper(List<get_subjects> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.subject_list,parent,false);
        return new viewhodler(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewhodler holder, int position) {
        holder.sname.setText(data.get(position).get_sub_name());
        callRetofit(position, holder);
    }

    private void callRetofit(int position, viewhodler holder) {
        Log.d("12345", "callRetofit: "+ position);
        Call<List<get_class_count>> call= apicontroller.getInstance()
                .getapi()
                .getclasscount(StudentProfile.rollno,
                data.get(position).getS_id());
        call.enqueue(new Callback<List<get_class_count>>() {
            @Override
            public void onResponse(Call<List<get_class_count>> call, Response<List<get_class_count>> response) {
                List<get_class_count> gd= response.body();
                Log.d("12345", "onResponse: " +gd.get(1).getCount1() + " " +position);
                holder.totalClass.setText(gd.get(0).getCount()+"");
                holder.classAttend.setText(gd.get(1).getCount1()+"");

            }

            @Override
            public void onFailure(Call<List<get_class_count>> call, Throwable t) {
                Log.d("12345", "onResponse:  " + t.getMessage());
            }
        });
//        Call<get_class_count> call= apicontroller.getInstance()
//                .getapi()
//                .getclasscount(StudentProfile.rollno,
//                        data.get(position).getS_id());
//        call.enqueue(new Callback<get_class_count>() {
//            @Override
//            public void onResponse(Call<get_class_count> call, Response<get_class_count> response) {
//                get_class_count gd= response.body();
//                Log.d("12345", "onResponse: " +gd.getCount()+  "  "+ gd.getCount1());
//
//            }
//
//            @Override
//            public void onFailure(Call<get_class_count> call, Throwable t) {
//                Log.d("12345", "onResponse:  " + t.getMessage());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
