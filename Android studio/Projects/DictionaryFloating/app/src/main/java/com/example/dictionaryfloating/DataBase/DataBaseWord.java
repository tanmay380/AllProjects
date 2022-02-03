package com.example.dictionaryfloating.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.dictionaryfloating.MainActivity;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataBaseWord extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WordsList";
    private static final String TABLE_CONTACTS = "Word";
    public static final String KEY_ID = "id";
    public static final String NAME = "word";


    public DataBaseWord(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void addWords(DataInputStream dataInputStream){
        int progress = 0;
        int total = 233467;

        SQLiteDatabase db = this.getWritableDatabase();
        Scanner sc = new Scanner(dataInputStream);

        while (sc.hasNextLine()){
            db.execSQL(sc.nextLine());
            MainActivity.progressDialog.setProgress((progress*100/total));
            progress++;
        }
//        db.close();
    }

    public ArrayList<String> findWords(String letter){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select " + NAME + " from " + TABLE_CONTACTS + " where word LIKE '"+letter +"%" +"'",new String[]{});
        Log.d("12345", "findWords: " + "Called");
        ArrayList<String> wrods = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            wrods.add(cursor.getString(0));
            cursor.moveToNext();
        }
        Log.d("12345", "findWords: " + wrods.size());
        return wrods;
    }
}
