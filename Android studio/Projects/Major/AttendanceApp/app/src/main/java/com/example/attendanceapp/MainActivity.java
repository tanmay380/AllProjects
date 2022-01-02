package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Button signup;
    EditText name, sapid, mobile, roll;
    Spinner branch;
    ArrayList<getClassname> classspinner;
    int class_id;
    String class_name;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        onStartup();

        signup = findViewById(R.id.signUp);
        name = findViewById(R.id.edtname);
        sapid = findViewById(R.id.edtsapid);
        mobile = findViewById(R.id.edtnumber);
        roll = findViewById(R.id.edtroll);
        branch = findViewById(R.id.edtbranch);


        retrofitClassIdCall();

        branch.setSelection(0, false);

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getClassname classname = (getClassname) parent.getItemAtPosition(position);
                class_id = classname.getC_id();
                class_name = classname.getCname();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkvalidation()) {
                    retrofitSignupCall(name.getText().toString(), roll.getText().toString(), class_id);
                }
            }
        });

    }

    private void retrofitSignupCall(String name, String rno, int class_id) {
        Call<signup_response_model> call = apicontroller.getInstance()
                .getapi()
                .getregister(rno, name, class_id);
        call.enqueue(new Callback<signup_response_model>() {
            @Override
            public void onResponse(Call<signup_response_model> call, Response<signup_response_model> response) {
                signup_response_model signup = response.body();
                String result = signup.getMessage();
                Log.d("12345", "onResponse: " + result + "  " + response.body());

                if (result.equals("exists")) {
                    startActivity(new Intent(MainActivity.this, homeScreen.class));
                    sharedPreference();
                    finish();
                }
                if (result.equals("inserted")) {
                    Toast.makeText(getApplicationContext(), "Profile has been creates", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, homeScreen.class));
                    sharedPreference();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<signup_response_model> call, Throwable t) {
                Log.d("12345", "onResponse: " + "on respne" + t.getMessage().toString());
            }
        });
    }

    private void retrofitClassIdCall() {
        Call<List<getClassname>> call = apicontroller.getInstance()
                .getapi()
                .getclassname();
        progress=new ProgressDialog(MainActivity.this);
        progress.setMessage("Please Wait");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        call.enqueue(new Callback<List<getClassname>>() {
            @Override
            public void onResponse(Call<List<getClassname>> call, Response<List<getClassname>> response) {
                List<getClassname> list = response.body();

                classspinner = new ArrayList<>();
                classspinner.add(new getClassname(-1, "Select your branch"));
                for (int i = 0; i < list.size(); i++) {
                    classspinner.add(new getClassname(list.get(i).getC_id(), list.get(i).getCname()));
                }
                ArrayAdapter<getClassname> adapter = new ArrayAdapter<getClassname>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, classspinner);
                branch.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<getClassname>> call, Throwable t) {
            }
        });
        progress.cancel();
    }

    private void sharedPreference() {
        SharedPreferences sp = getSharedPreferences("Information", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("logged", "yes");
        editor.putString("Name", name.getText().toString());
        editor.putString("Sapid", sapid.getText().toString());
        editor.putString("Mobile", mobile.getText().toString());
        editor.putString("Roll", roll.getText().toString());
        editor.putString("Branch", class_name);
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
        if (class_id == -1) {
            Toast.makeText(getApplicationContext(), "Please Select A Respective Branch", Toast.LENGTH_SHORT).show();
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
        if (sp1.contains("logged")) {
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