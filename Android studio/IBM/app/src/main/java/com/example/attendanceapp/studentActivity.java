package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class studentActivity extends AppCompatActivity {

    Toolbar toolbar;
    private String className;
    private String subjectName;
    private int position;
    private long cid;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StudentItem> studentItems = new ArrayList<>();
    private DbHelper dbHelper;

    MyCalender myCalender;
    TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        dbHelper = new DbHelper(this);



        subtitle = findViewById(R.id.subtitle_toolbar);
        Intent intent = getIntent();
        className = intent.getStringExtra("classname");
        subjectName = intent.getStringExtra("subjectname");
        position = intent.getIntExtra("position", -1);
        cid =intent.getLongExtra("cid", -1);
//        Toast.makeText(getApplicationContext(), Integer.toString(cid), Toast.LENGTH_SHORT).show();

        myCalender = new MyCalender();

        loadData();
        setToolbar();


        recyclerView = findViewById(R.id.student_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        studentAdapter = new StudentAdapter(this, studentItems);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setOnItemClickListener(position1 -> changeState(position1));
        loadStatusDta();
    }

    private void loadData() {
        Cursor cursor = dbHelper.getStudentTable(cid);

        studentItems.clear();
        while (cursor.moveToNext()) {
            long sid = cursor.getLong(cursor.getColumnIndex(DbHelper.S_ID));
            int roll = cursor.getInt(cursor.getColumnIndex(DbHelper.STUDENT_ROLL_KEY));
            String name = cursor.getString(cursor.getColumnIndex(DbHelper.STUDENT_NAME_KEY));

            studentItems.add(new StudentItem(sid, roll, name));
        }

    }

    private void changeState(int position1) {
        String Status = studentItems.get(position1).getStatus();
        if (Status.equals("P")) Status = "A";
        else Status = "P";

        studentItems.get(position1).setStatus(Status);
        studentAdapter.notifyItemChanged(position1);
    }

    private void setToolbar() {

        toolbar = findViewById(R.id.toolbar);
        TextView title = findViewById(R.id.title_toolbar);
        ImageButton back = findViewById(R.id.back);
        ImageButton save = findViewById(R.id.save_button);

        save.setOnClickListener(v-> savestatus());
        title.setText(className);
        subtitle.setText(subjectName+"- " +myCalender.getDate());
        back.setOnClickListener(v -> onBackPressed());
        toolbar.inflateMenu(R.menu.student_menu);
        toolbar.setOnMenuItemClickListener(menuItem -> onMenuItemClic(menuItem));

    }

    private void savestatus() {
        for (StudentItem studentItem: studentItems){
            String status = studentItem.getStatus();
            if (status!="P") status="A";
            long value = dbHelper.addStatus(studentItem.getSid(), cid ,myCalender.getDate(), status);

            if (value==-1){
                dbHelper.updateStatus(studentItem.getSid(), myCalender.getDate(), status);
            }
        }
    }

    private void loadStatusDta(){
        for (StudentItem studentItem: studentItems) {
            String status = dbHelper.getStatus(studentItem.getSid(), myCalender.getDate());
            if (status != null) studentItem.setStatus(status);
            else
                studentItem.setStatus("");
        }
        studentAdapter.notifyDataSetChanged();
    }

    private boolean onMenuItemClic(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.addstudent) {
            showaddStudentDialogue();
        }
        if (menuItem.getItemId() == R.id.show_calender) {
            showCalender();
        }
        if (menuItem.getItemId() == R.id.show_attendance) {
            showsheetAttendance();
        }
        return true;
    }

    private void showsheetAttendance() {
        long[] id = new long[studentItems.size()];
        long[] rollarray = new long[studentItems.size()];
        String[] nameArray = new String[studentItems.size()];

        for (int i=0; i<id.length;i++){
            id[i]=studentItems.get(i).getSid();
        }
        for (int i=0; i<rollarray.length;i++){
            rollarray[i]=studentItems.get(i).getRoll();
        }
        for (int i=0; i<id.length;i++){
            nameArray[i]=studentItems.get(i).getName();
        }

        Intent intent = new Intent(this, SheetListActivity.class);
        intent.putExtra("cid", cid);
        intent.putExtra("idarray", id);
        intent.putExtra("rollarray", rollarray);
        intent.putExtra("nameArray", nameArray);
        startActivity(intent);
    }

    private void showCalender() {
        myCalender.show(getSupportFragmentManager(), "");
        myCalender.setOnCalenderClickLister(this::onCalenderokcliclk);
    }

    private void onCalenderokcliclk(int year, int month, int day) {
        myCalender.setDate(year, month, day);
        subtitle.setText(subjectName +"- "+myCalender.getDate());
        loadStatusDta();
    }


    private void showaddStudentDialogue() {
        myDialogue dialogue = new myDialogue();
        dialogue.show(getSupportFragmentManager(), myDialogue.STUDENT_AND_DIALOGUE);
        dialogue.setListener(this::addStudent);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0: showupdatestudent(item.getGroupId());
                break;
            case 1:deleteStudent(item.getGroupId());
                    break;
        }
        return super.onContextItemSelected(item);
    }

    private void showupdatestudent(int position) {
        myDialogue myDialogue = new myDialogue(studentItems.get(position).getRoll(), studentItems.get(position).getName());
        myDialogue.show(getSupportFragmentManager(), myDialogue.STUDENT_UPDATE_DIALOGE);
        myDialogue.setListener((roll_string, name) -> updateStudent(position, name));
    }

    private void updateStudent(int position,  String name) {
        dbHelper.updateStudent(studentItems.get(position).getSid(), name);
        studentItems.get(position).setName(name);
        studentAdapter.notifyItemChanged(position);
    }

    private void deleteStudent(int position) {
        dbHelper.deleteStudent(studentItems.get(position).getSid());
        dbHelper.deleteStatus(studentItems.get(position).getSid());
        studentItems.remove(position);
        studentAdapter.notifyItemRemoved(position);
    }

    private void addStudent(String roll_string, String name) {
        int roll = Integer.parseInt(roll_string);
        long sid = dbHelper.addStudent(cid, roll, name);
        StudentItem studentItem = new StudentItem(sid, roll, name);
        studentItems.add(studentItem);
        studentAdapter.notifyDataSetChanged();
    }


}