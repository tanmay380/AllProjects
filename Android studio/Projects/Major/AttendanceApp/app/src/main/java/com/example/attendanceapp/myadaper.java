package com.example.attendanceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendanceapp.retrofit.get_subjects;

import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
