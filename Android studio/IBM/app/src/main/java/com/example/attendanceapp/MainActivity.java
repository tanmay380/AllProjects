package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;// recycler view part

    RecyclerView.LayoutManager layoutManager;// recycler view part
    ArrayList<ClassItem> classItems = new ArrayList<>();

    EditText class_edt;
    EditText subject_edt;
    Toolbar toolbar;

    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fabbutton);
        fab.setOnClickListener(v -> showDialog());

        dbHelper = new DbHelper(this);

        loadData();

        recyclerView = findViewById(R.id.recyclerView);



        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdapter = new ClassAdapter(this, classItems);
        recyclerView.setAdapter(classAdapter);
        classAdapter.setOnItemClickListener(this::gotoItemActivity);

        setToolbar();


    }

    private void loadData() {
        Cursor cursor = dbHelper.getClassTable();

        classItems.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(DbHelper.C_ID));
            String  className = cursor.getString(cursor.getColumnIndex(DbHelper.CLASS_NAME_KEY));
            String  subjectName = cursor.getString(cursor.getColumnIndex(DbHelper.SUBJECT_NAME_KEY));

            classItems.add(new ClassItem(id, className, subjectName));
        }

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
        intent.putExtra("cid", classItems.get(position).getCid());
//        Toast.makeText(getApplicationContext(), Long.toString(classItems.get(position).getCid()), Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }

    private void showDialog() {
        myDialogue dialogue = new myDialogue();
        dialogue.show(getSupportFragmentManager(), myDialogue.CLASS_AND_DIALOGUE);
        dialogue.setListener(this::addClass);

    }

    private void addClass(String classname, String subjectname) {
        long cid=  dbHelper.addClass(classname, subjectname);
        ClassItem classItem = new ClassItem(cid, classname, subjectname);
        classItems.add(classItem);
        classAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(getApplicationContext(),Integer.toString(item.getItemId()), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case 0:
                showUpdateDialog(item.getGroupId());
                break;
            case 1: deleteClass(item.getGroupId());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int position) {
        myDialogue mydialogue = new myDialogue();
        mydialogue.show(getSupportFragmentManager(), myDialogue.CLASS_UPDATE_DIALOG);
        mydialogue.setListener((classname, subjectname) -> updateClass(position, classname, subjectname));
    }

    private void updateClass(int position, String classnmae, String subjectname) {
        dbHelper.updateClass(classItems.get(position).getCid(), classnmae, subjectname);
        classItems.get(position).setClassName(classnmae);
        classItems.get(position).setSubjectName(subjectname);
        classAdapter.notifyItemChanged(position);

    }

    private void deleteClass(int position) {
        dbHelper.deleteClass(classItems.get(position).getCid());
        classItems.remove(position);
        classAdapter.notifyItemRemoved(position);
    }
}