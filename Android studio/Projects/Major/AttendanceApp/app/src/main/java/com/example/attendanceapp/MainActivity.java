package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.attendanceapp.retrofit.*;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button signup;
    EditText name, sapid, mobile, roll;
    Spinner branch;
    ArrayList<getClassname> classspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

//        onStartup();

        signup = findViewById(R.id.signUp);
        name = findViewById(R.id.edtname);
        sapid = findViewById(R.id.edtsapid);
        mobile = findViewById(R.id.edtnumber);
        roll = findViewById(R.id.edtroll);
        branch = findViewById(R.id.edtbranch);

        retrofitCall();
        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getClassname classname = (getClassname) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), classname.getCname(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void retrofitCall() {
        Call<List<getClassname>> call = apicontroller.getInstance()
                .getapi()
                .getclassname();
        call.enqueue(new Callback<List<getClassname>>() {
            @Override
            public void onResponse(Call<List<getClassname>> call, Response<List<getClassname>> response) {
                List<getClassname> list = response.body();

                classspinner= new ArrayList<>();
                for(int i=0; i<list.size();i++){
                    Log.d("12345", "onResponse: " + list.get(i).getC_id());
                    classspinner.add(new getClassname(list.get(i).getC_id(), list.get(i).getCname()));
                }
                ArrayAdapter<getClassname> adapter= new ArrayAdapter<getClassname>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, classspinner);
                branch.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<getClassname>> call, Throwable t) {
                Log.d("12345", "onResponse: " + t.toString());
            }
        });
    }

    private void sharedPreference() {
        SharedPreferences sp = getSharedPreferences("Information", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loggedin", "yes");
        editor.putString("Name", name.getText().toString());
        editor.putString("Sapid", sapid.getText().toString());
        editor.putString("Mobile", mobile.getText().toString());
        editor.putString("Roll", roll.getText().toString());
        editor.apply();
    }

    private boolean checkvalidation() {
        if (name.getText().toString().isEmpty()) {
            name.setError("This field is important");
            return false;
        }
        if (mobile.getText().toString().isEmpty()) {
            mobile.setError("This field is important");
            return false;
        }
        if (sapid.getText().toString().isEmpty()) {
            sapid.setError("This field is important");
            return false;
        }
        if (roll.getText().toString().isEmpty()) {
            roll.setError("This field is important");
            return false;
        }
        return true;
    }

    private void onStartup() {
        SharedPreferences sp1 = getSharedPreferences("Information", MODE_PRIVATE);
        if (sp1.contains("loggedin")) {
            startActivity(new Intent(getApplicationContext(), homeScreen.class));
            finish();
        } else {


            Dexter.withContext(getApplicationContext())
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }
                    }).check();
        }
    }
}