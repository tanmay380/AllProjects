package com.example.dbtest;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "Words";
    public static final String KEY_ID = "id";
    public static final String NAME = "word";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("12345", "onCreate: " + "created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    void addWords(DataInputStream dataInputStream){
        SQLiteDatabase db = this.getWritableDatabase();
        Scanner sc = new Scanner(dataInputStream);
        ContentValues contentValues = new ContentValues();
        Log.d("12345", "addWords: " + "started");
        int i=0;
        while (sc.hasNextLine()){
//            MainActivity.textView.setText(i+"");
            String word = sc.nextLine();

            String query = "select word from Words where word = \""+ word +"\"";
            try {
            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
//                Log.d("12345", "addWords: " + i);
                if(cursor.getCount()<1)
                    contentValues.put(NAME, word);
                else{
                    Log.d("12345", "addWords: " + word);
                }
            }catch (Exception e){
                Log.d("12345", "addWords: " + e.getMessage());
            }

            db.insert(TABLE_CONTACTS,NAME,contentValues);
            i++;
        }

        Log.d("12345", "onCreate: " + "inserted");
//        db.close();

    }

    void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + TABLE_CONTACTS);
        db.close();
    }

    void CreateDaabse(DataInputStream dataInputStream, Context context ){
        int progress = 1;
        int total = 370883;
        Log.d("12345", "onCreate: " + "Started");
        SQLiteDatabase db = this.getWritableDatabase();
//        if (db.)
        Scanner sc = new Scanner(dataInputStream);
       while (sc.hasNextLine()){
           try {
           db.execSQL(sc.nextLine());
               Log.d("12345", "CreateDaabse: " +total + "  " +    "  " + progress);
           progress++;
           }catch (SQLiteException e){
               break;
           }
       }
    }

}
