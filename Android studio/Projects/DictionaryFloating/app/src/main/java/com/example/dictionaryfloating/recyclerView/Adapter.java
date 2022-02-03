package com.example.dictionaryfloating.recyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.dictionaryfloating.MainActivity;
import com.example.dictionaryfloating.R;
import com.example.dictionaryfloating.RoomDatabase.*;
import com.google.gson.Gson;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<ModelClass> arrayList;
    Context context;
    HashMap<String, String> app;


    public Adapter(ArrayList ap, Context context, HashMap<String, String> app) {
        this.arrayList = ap;
        this.context = context;
        this.app = app;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppDatabase appDatabase = Room.databaseBuilder(holder.itemView.getContext(), AppDatabase.class, "AppName")
                .allowMainThreadQueries()
                .build();
        UserDao userDao = appDatabase.userDao();
        List<User> list = userDao.selectedApps();
        ArrayList<String> appPackage = MainActivity.appPackage;

        final ModelClass modelClass = arrayList.get(position);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(modelClass.getAppname())) {
                modelClass.setChecked(true);
                if (!appPackage.contains(app.get(modelClass.getAppname()))) {
                    appPackage.add(app.get(modelClass.getAppname()));
                }
            }
        }
        holder.name.setText(modelClass.getAppname());
        holder.img.setImageDrawable(modelClass.getResource());
        holder.box.setOnCheckedChangeListener(null);
        holder.box.setChecked(modelClass.isChecked());

        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                modelClass.setChecked(isChecked);
                if (isChecked && (userDao.exits(modelClass.appname) == 0)) {
                    userDao.insert(modelClass.appname);
                    appPackage.add(app.get(modelClass.getAppname()));
                }
                if (!isChecked) {
                    userDao.delete(modelClass.appname);
                    appPackage.remove(app.get(modelClass.getAppname()));
                }

                SharedPreferences sp = context.getSharedPreferences("AppPackageName", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                Gson gson = new Gson();
                Log.d("12345", "onCheckedChanged: " + Arrays.toString(appPackage.toArray()));

                String json = gson.toJson(appPackage);
                editor.putString("appPackage", json);
                editor.apply();
            }
        });
        appDatabase.close();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private CheckBox box;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.appname);
            box = itemView.findViewById(R.id.checkbox);
            img = itemView.findViewById(R.id.icon);
        }
    }

    @Override
    public int getItemCount() {
//        Log.d("12345", "getItemCount: " + arrayList.size());
        return arrayList.size();
    }

}
