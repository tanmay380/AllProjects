package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

public class StudentProfile extends AppCompatActivity {
    TextView name,branch,mobile,sapid,rollnnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        name=findViewById(R.id.prf_name);
        branch=findViewById(R.id.prf_branch);
        mobile=findViewById(R.id.prf_number);
        sapid=findViewById(R.id.prf_sapid);
        rollnnum=findViewById(R.id.prf_roll);

        SharedPreferences sp= getSharedPreferences("Information", MODE_PRIVATE);
        name.setText(sp.getString("Name", null));
        branch.setText(sp.getString("Branch", null));
        sapid.setText(sp.getString("Sapid", null));
        rollnnum.setText(sp.getString("Roll", null));
        mobile.setText(sp.getString("Mobile", null));
    }
}