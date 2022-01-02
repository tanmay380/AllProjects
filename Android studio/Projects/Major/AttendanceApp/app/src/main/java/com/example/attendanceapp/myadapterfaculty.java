package com.example.attendanceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendanceapp.retrofit.getFaculty;

import java.util.List;

public class myadapterfaculty extends RecyclerView.Adapter<myadapterfaculty.ViewHolder>{
    List<getFaculty> list;

    public myadapterfaculty(List<getFaculty> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.faculty_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.f_name.setText(list.get(position).getF_name());
    holder.sname.setText(list.get(position).getSname());

    holder.f_name.setOnClickListener(v -> Toast.makeText(v.getContext(),"   dsfdf", Toast.LENGTH_SHORT).show());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView f_name,sname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.f_name = itemView.findViewById(R.id.fname);
            this.sname = itemView.findViewById(R.id.sname);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
