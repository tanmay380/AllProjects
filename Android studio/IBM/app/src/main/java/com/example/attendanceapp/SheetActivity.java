package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Interpolator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SheetActivity extends AppCompatActivity {

    Toolbar toolbar;
    String month;
    HSSFWorkbook hssfWorkbook;
    HSSFSheet hssfSheet;
    HSSFRow hssfRow;
    HSSFCell hssfCell;
    File file, file1, filepath;
    int cid;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet2);

        dbHelper = new DbHelper(this);

        month = getIntent().getStringExtra("month");
        cid = (int) getIntent().getIntExtra("cid", -1);

        String[] clasnmae = dbHelper.searchclassName(cid);
        Log.d("classnaem", "onCreate: " + Arrays.toString(clasnmae));


        file = new File(Environment.getExternalStorageDirectory() + String.format("/AttendanceApp/%s/%s", clasnmae[0], clasnmae[1]));

        filepath = new File(Environment.getExternalStorageDirectory() + String.format("/AttendanceApp/%s/%s", clasnmae[0], clasnmae[1]) + "/" + month + ".xls");
        setToolbar();
        hssfWorkbook = new HSSFWorkbook();
        hssfSheet = hssfWorkbook.createSheet();
        showTable();


    }

    private void setToolbar() {
        TextView title = findViewById(R.id.title_toolbar);
        TextView sub = findViewById(R.id.subtitle_toolbar);
        ImageButton back = findViewById(R.id.back);
        ImageButton save = findViewById(R.id.save_button);

        title.setText(month);
        sub.setVisibility(View.GONE);

        back.setOnClickListener(v -> onBackPressed());
        save.setOnClickListener(v -> saveExcelSheet());
    }

    private void saveExcelSheet() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        saveToExcel();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }


                }).check();
    }

    private void saveToExcel() {

        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!filepath.exists()) {

                filepath.createNewFile();
            }


            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            hssfWorkbook.write(fileOutputStream);


            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void showTable() {


        TableLayout tableLayout = findViewById(R.id.tablelayout);
        long[] id = getIntent().getLongArrayExtra("idarray");

        long[] rollarray = getIntent().getLongArrayExtra("rollarray");

        String[] nameArray = getIntent().getStringArrayExtra("nameArray");

        String month = getIntent().getStringExtra("month");

        int DAY_IN_MONTH = getDayinMonth(month);

        //row setup
        int rowsize = id.length + 1;
        TableRow[] rows = new TableRow[rowsize];
        TextView[] tv_rolls = new TextView[rowsize];
        TextView[] name_tv = new TextView[rowsize];
        TextView[][] status_tvs = new TextView[rowsize][DAY_IN_MONTH + 1];

        for (int i = 0; i < rowsize; i++) {
            tv_rolls[i] = new TextView(this);
            name_tv[i] = new TextView(this);
            for (int j = 1; j <= DAY_IN_MONTH; j++) {

                status_tvs[i][j] = new TextView(this);
            }
        }
        //header
        tv_rolls[0].setText("Roll");
        hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(0);
        hssfCell.setCellValue("Roll");
        tv_rolls[0].setTypeface(tv_rolls[0].getTypeface(), Typeface.BOLD);

        name_tv[0].setText("Name");
        hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue("Name");
        name_tv[0].setTypeface(name_tv[0].getTypeface(), Typeface.BOLD);
//        status_tvs[0][0].setText("hello");
        for (int i = 1; i <= DAY_IN_MONTH; i++) {
            status_tvs[0][i].setText(i + "");
//            hssfRow = hssfSheet.createRow(0);
            hssfCell = hssfRow.createCell(i + 1);
            hssfCell.setCellValue(i + "");
            status_tvs[0][i].setTypeface(status_tvs[0][i].getTypeface(), Typeface.BOLD);
        }


        for (int i = 1; i < rowsize; i++) {
            tv_rolls[i].setText(String.valueOf(rollarray[i - 1]));
            HSSFRow hssfRow1 = hssfSheet.createRow(i);
            hssfCell = hssfRow1.createCell(0);
            hssfCell.setCellValue(rollarray[i - 1]);

            name_tv[i].setText(nameArray[i - 1]);
            hssfCell = hssfRow1.createCell(1);
            hssfCell.setCellValue(nameArray[i - 1]);

            for (int j = 1; j <= DAY_IN_MONTH; j++) {
                String day = String.valueOf(j);
                if (day.length() == 1) day = "0" + day;
                String date = day + "-" + month;
                String status = dbHelper.getStatus(id[i - 1], date);
                hssfCell = hssfRow1.createCell(j + 1);
                hssfCell.setCellValue(status);

                status_tvs[i][j].setText(status);
            }
        }

        for (int i = 0; i < rowsize; i++) {
            rows[i] = new TableRow(this);
            rows[i].setPadding(16, 16, 16, 16);
            name_tv[i].setPadding(16, 16, 16, 16);
            rows[i].addView(tv_rolls[i]);
            rows[i].addView(name_tv[i]);
            for (int j = 1; j <= DAY_IN_MONTH; j++) {

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