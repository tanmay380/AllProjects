package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText prefixedt,numberedt;
    Button button;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        prefixedt = findViewById(R.id.prefix);
        numberedt = findViewById(R.id.number);
        button = findViewById(R.id.submit);
        button.setOnClickListener(v->openWhatsApp());

    }

    private void openWhatsApp() {
        String number = numberedt.getText().toString().trim();
        String prefix = prefixedt.getText().toString().trim();
        String url = "https://api.whatsapp.com/send?phone="+prefix+number;
        try {
            PackageManager pm = getApplicationContext().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Toast.makeText(getApplicationContext(), pm.toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}