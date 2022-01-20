package com.example.floatingwindows;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    Button button;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button  = findViewById(R.id.head_btn);
        edt= findViewById(R.id.main_edt);

        if(isServiceRunning()){
            stopService(new Intent(this, FloatingWindowApp.class));
        }

        edt.setText(Common.currDes);
        edt.setSelection(edt.getText().toString().length());

        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Common.currDes = edt.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOverlayPermission()){
                    startService(new Intent(MainActivity.this, FloatingWindowApp.class));
                    finish();
                }
                else
                    requestfloatingPermission();
            }
        });

    }

    public boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo act : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (FloatingWindowApp.class.getName().equals(act.service.getClassName()))
                return true;
        }
        return false;
    }

    private void requestfloatingPermission(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.setCancelable(true)
                .setTitle("Screen Overlay Permission")
                .setMessage("Enable it to display over the app from setting")
                .setPositiveButton("true", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:"+ getPackageName()));
                        startActivityForResult(intent , RESULT_OK);
                    }
                }).create();
        alertDialog.show();
    }

    private boolean checkOverlayPermission(){
          if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
              return Settings.canDrawOverlays(this);
        }
          return  false;
    }
}