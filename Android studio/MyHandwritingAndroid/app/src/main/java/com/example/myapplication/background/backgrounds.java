package com.example.myapplication.background;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class backgrounds extends AppCompatActivity {
     Context context;

    public void showError(final Exception e){
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(Log.getStackTraceString(e))
                .setPositiveButton("Save to file", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"DOE",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
        return;

    }
}
