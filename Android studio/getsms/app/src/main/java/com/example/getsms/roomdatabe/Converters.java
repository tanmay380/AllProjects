package com.example.getsms.roomdatabe;

import android.util.Log;
import android.util.Pair;

import androidx.room.TypeConverter;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromPair(String value) {
        Log.d("12345", "fromPair: first");
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
//        String s= value.first + ":" + value.second;
//        String s= value;
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Log.d("12345", "fromArrayList: second");
        Gson gson = new Gson();
//        int size = list.size();
//        for (int i = 0; i < size; i++) {
//            Pair p = list.get(i);
//            String s = p.first +":"+p.second;
//            list.add(i,new Pair(s,null));
//        }
        String json = gson.toJson(list);
        Log.d("12345", "fromArrayList: " + json);
        return json;
    }
}