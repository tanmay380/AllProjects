package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class SheetActivity extends AppCompatActivity {

    private ArrayAdapter arrayAdapter;
    ListView sheetList;
    private ArrayList listItems = new ArrayList();
    private  int cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        cid=(int) getIntent().getLongExtra("cid",-1);
        Toast.makeText(getApplicationContext(), Integer.toString(cid), Toast.LENGTH_LONG).show();
        loadListItems();


        sheetList = findViewById(R.id.shetList);
        arrayAdapter=  new ArrayAdapter(this, R.layout.sheetlist, R.id.datelistitem,listItems) ;
        sheetList.setAdapter(arrayAdapter);

    }

    private void loadListItems() {
        Cursor cursor = new DbHelper(this).getDitsinctMonths(cid);

        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(DbHelper.DATE_KEY));
            listItems.add(date.substring(3));
        }

    }
}