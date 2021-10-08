package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SheetListActivity extends AppCompatActivity {

    private ArrayAdapter arrayAdapter;
    ListView sheetList;
    private ArrayList<String> listItems = new ArrayList();
    private  int cid;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);

        toolbar = findViewById(R.id.toolbar);
        setToolbar();

        cid=(int) getIntent().getLongExtra("cid",-1);
        Toast.makeText(getApplicationContext(), Integer.toString(cid), Toast.LENGTH_LONG).show();
        loadListItems();


        sheetList = findViewById(R.id.shetList);
        arrayAdapter=  new ArrayAdapter(this, R.layout.sheetlist, R.id.datelistitem,listItems) ;
        sheetList.setAdapter(arrayAdapter);
        sheetList.setOnItemClickListener((parent, view, position, id) -> openSheetActivity(position));

    }

    private void setToolbar() {
        TextView title= findViewById(R.id.title_toolbar);
        TextView sub= findViewById(R.id.subtitle_toolbar);
        ImageButton back= findViewById(R.id.back);
        ImageButton save= findViewById(R.id.save_button);

        title.setVisibility(View.INVISIBLE);
        sub.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

        back.setOnClickListener(v-> onBackPressed());


    }

    private void openSheetActivity(int position) {
        long[] id = getIntent().getLongArrayExtra("idarray");
        long[] rollarray = getIntent().getLongArrayExtra("rollarray");
        String[] nameArray = getIntent().getStringArrayExtra("nameArray");
        Intent intent = new Intent(this, SheetActivity.class);
        intent.putExtra("cid", cid);
        intent.putExtra("idarray", id);
        intent.putExtra("rollarray", rollarray);
        intent.putExtra("nameArray", nameArray);
        intent.putExtra("month", listItems.get(position));


        startActivity(intent);
    }

    private void loadListItems() {
        Cursor cursor = new DbHelper(this).getDitsinctMonths(cid);

        while (cursor.moveToNext()){
            String date = cursor.getString(cursor.getColumnIndex(DbHelper.DATE_KEY));
            listItems.add(date.substring(3));
        }

    }
}