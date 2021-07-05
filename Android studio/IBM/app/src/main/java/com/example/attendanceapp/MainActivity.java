package com.example.attendanceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClassItem> classItems = new ArrayList<>();

    EditText class_edt;
    EditText subject_edt;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fabbutton);
        fab.setOnClickListener(v -> showDialog());

        recyclerView = findViewById(R.id.recyclerView);



        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdapter = new ClassAdapter(this, classItems);
        recyclerView.setAdapter(classAdapter);
        classAdapter.setOnItemClickListener(this::gotoItemActivity);

        setToolbar();


    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = findViewById(R.id.title_toolbar);
        TextView subtitle = findViewById(R.id.subtitle_toolbar);
        ImageButton back = findViewById(R.id.back);
        ImageButton save = findViewById(R.id.save_button);

        title.setText("Attendance App");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position){
        Intent intent = new Intent(this, studentActivity.class );

        intent.putExtra("classname",classItems.get(position).getClassName());
        intent.putExtra("subjectname",classItems.get(position).getSubjectName());
        intent.putExtra("position", position);
        startActivity(intent);

    }

    private void showDialog() {
        myDialogue dialogue = new myDialogue();
        dialogue.show(getSupportFragmentManager(), myDialogue.CLASS_AND_DIALOGUE);
        dialogue.setListener(this::addClass);

    }

    private void addClass(String classname, String subjectname) {

        classItems.add(new ClassItem(classname, subjectname));
        classAdapter.notifyDataSetChanged();
    }
}