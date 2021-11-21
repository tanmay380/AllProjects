package com.example.attendanceapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewhodler extends RecyclerView.ViewHolder {
    TextView totalClass, classAttend, sname;


    public viewhodler(@NonNull View itemView) {
        super(itemView);
        totalClass= itemView.findViewById(R.id.tclass);
        sname= itemView.findViewById(R.id.sname);
        classAttend= itemView.findViewById(R.id.yclass);
    }
}
