package com.example.elderlyhelperapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlarmViewAdaptor extends ArrayAdapter<AlarmView> {


    public AlarmViewAdaptor(@NonNull Context context, ArrayList<AlarmView> alarmViews) {
        super(context, 0, alarmViews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView= convertView;
        currentItemView= LayoutInflater.from(getContext()).inflate(R.layout.textalarm, parent, false);
        AlarmView currentposition= getItem(position);

        TextView textView= currentItemView.findViewById(R.id.label);
        textView.setText(currentposition.getText());

        return currentItemView;
    }
}
