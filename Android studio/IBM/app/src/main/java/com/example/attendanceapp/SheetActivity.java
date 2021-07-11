package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SheetActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet2);

        showTable();

    }

    private void showTable() {

        DbHelper dbHelper = new DbHelper(this);

        TableLayout tableLayout = findViewById(R.id.tablelayout);
        long[] id = getIntent().getLongArrayExtra("idarray");
        long[] rollarray = getIntent().getLongArrayExtra("rollarray");
        String[] nameArray = getIntent().getStringArrayExtra("nameArray");
        String month = getIntent().getStringExtra("month");

        int DAY_IN_MONTH = getDayinMonth(month);
        Log.d("12345", "showTable: " +DAY_IN_MONTH);

        //row setup
        int rowsize = id.length + 1;
        TableRow[] rows = new TableRow[rowsize];
        TextView[] tv_rolls = new TextView[rowsize];
        TextView[] name_tv = new TextView[rowsize];
        TextView[][] status_tvs = new TextView[rowsize][DAY_IN_MONTH + 1];

        for (int i = 0; i < rowsize; i++) {
            tv_rolls[i] = new TextView(this);
            name_tv[i] = new TextView(this);
            for (int j = 0; j < DAY_IN_MONTH; j++){

                status_tvs[i][j] = new TextView(this);
            }
        }
        //header
        tv_rolls[0].setText("Roll");
        tv_rolls[0].setTypeface(tv_rolls[0].getTypeface(), Typeface.BOLD);

        name_tv[0].setText("Name");
        name_tv[0].setTypeface(name_tv[0].getTypeface(), Typeface.BOLD);
//        status_tvs[0][0].setText("hello");
        for (int i = 0; i < DAY_IN_MONTH; i++) {
            status_tvs[0][i].setText(i+1+"");
//            status_tvs[0][i].setText(String.valueOf(i));
            status_tvs[0][i].setTypeface(status_tvs[0][i].getTypeface(), Typeface.BOLD);
        }


        for (int i = 1; i < rowsize; i++) {
            tv_rolls[i].setText(String.valueOf(rollarray[i - 1]));
            name_tv[i].setText(nameArray[i - 1]);
            for (int j = 0; j < DAY_IN_MONTH; j++) {
                String day = String.valueOf(i);
                if (day.length() == 1) day = "0" + day;

                String status = dbHelper.getStatus(id[i - 1], day + "." + month);
                status_tvs[i][j].setText(status);
            }
        }

        for (int i = 0; i < rowsize; i++) {
            rows[i] = new TableRow(this);
            rows[i].setPadding(16, 16, 16, 16);
            name_tv[i].setPadding(16, 16, 16, 16);
            rows[i].addView(tv_rolls[i]);
            rows[i].addView(name_tv[i]);
            for (int j = 0; j < DAY_IN_MONTH; j++) {

                status_tvs[i][j].setPadding(16, 16, 16, 16);
                rows[i].addView(status_tvs[i][j]);
            }
            tableLayout.addView(rows[i]);
        }
        tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_MIDDLE);


    }

    private int getDayinMonth(String month) {
        int monthIndex = Integer.parseInt(month.substring(0, 2)) - 1;
        int year = Integer.parseInt(month.substring(3));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, monthIndex);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}