package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class studentActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String className;
    private String subjectName;
    private int position;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StudentItem> studentItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent = getIntent();
         className = intent.getStringExtra("classname");
         subjectName = intent.getStringExtra("subjectname");
         position = intent.getIntExtra("position", -1);

        setToolbar();

        recyclerView = findViewById(R.id.student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        studentAdapter  =  new StudentAdapter(this, studentItems);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setOnItemClickListener(position1 -> changeState(position1));
    }

    private void changeState(int position1) {
        String Status= studentItems.get(position1).getStatus();
        if (Status.equals("P") ) Status="A";
        else Status="P";

        studentItems.get(position1).setStatus(Status);
        studentAdapter.notifyItemChanged(position1);
    }

    private void setToolbar() {

        toolbar = findViewById(R.id.toolbar);
        TextView title = findViewById(R.id.title_toolbar);
        TextView subtitle = findViewById(R.id.subtitle_toolbar);
        ImageButton back = findViewById(R.id.back);
        ImageButton save = findViewById(R.id.save_button);

        title.setText(className);
        subtitle.setText(subjectName);
        back.setOnClickListener(v-> onBackPressed());
        toolbar.inflateMenu(R.menu.student_menu);
        toolbar.setOnMenuItemClickListener(menuItem-> onMenuItemClic(menuItem));

    }

    private boolean onMenuItemClic(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.addstudent){
            showaddStudentDialogue();
        }
        return true;
    }

    private void showaddStudentDialogue() {
        myDialogue dialogue = new myDialogue();
        dialogue.show(getSupportFragmentManager(), myDialogue.STUDENT_AND_DIALOGUE);
        dialogue.setListener(this::addStudent);
    }

    private void addStudent(String roll, String name) {
        studentItems.add(new StudentItem(roll, name));
        studentAdapter.notifyDataSetChanged();
    }


}