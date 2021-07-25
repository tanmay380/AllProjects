package com.example.excelsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.textview);

        try {
            InputStream myInput;
            AssetManager assetManager = getAssets();

            myInput = assetManager.open("codes.xls");
            POIFSFileSystem myFleSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkbook = new HSSFWorkbook(myFleSystem);
            HSSFSheet mySheet = myWorkbook.getSheetAt(0);

            Iterator<Row> rowIter = mySheet.rowIterator();
            int rowno =0;
            textView.append("\n");
            while (rowIter.hasNext()) {
                Log.e("TAG", " row no "+ rowno );
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    String sno="", det="";
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            sno = myCell.toString();
                        }else if (colno==2){
                            det = myCell.toString();
                        }
                        colno++;
                        Log.e("TAG", " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
                    }
                    textView.append( sno + " -- "+"  -- "+ det+"\n");
                }
                rowno++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}