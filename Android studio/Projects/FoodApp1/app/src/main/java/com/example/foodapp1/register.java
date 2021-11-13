package com.example.foodapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp1.retrofitfiles.apicontroller;
import com.example.foodapp1.retrofitfiles.signp_response_model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {
    EditText reg_emid, reg_mob, reg_pas;
    Button regsubmit;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        reg_emid = findViewById(R.id.reg_emid);
        reg_mob = findViewById(R.id.reg_mob);
        reg_pas = findViewById(R.id.reg_pas);
        regsubmit = findViewById(R.id.regsub);
        tv = findViewById(R.id.login_result);

        regsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = reg_emid.getText().toString().trim();
                String mobile = reg_mob.getText().toString().trim();
                String password = reg_pas.getText().toString().trim();
                Log.d("12345", "onClick: " + emailid+ " " + mobile + " " + password);
//                registeruser(emailid, mobile, password);
                Intent intent = new Intent(register.this,verifyotp.class);
                intent.putExtra("email",emailid);
                intent.putExtra("pass",password);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
            }
        });

    }

    private void registeruser(String emailid, String mobile, String password) {
        String name = "not applicable";
        String address = "not applicable";

        Call<signp_response_model> call = apicontroller.getInstance()
                .getapi()
                .getregister(
                        name, emailid, password, mobile, address
                );
        call.enqueue(new Callback<signp_response_model>() {
            @Override
            public void onResponse(Call<signp_response_model> call, Response<signp_response_model> response) {
                signp_response_model obj = response.body();
                assert obj != null;
                String result = obj.getMessage().trim();
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                if (result.equals("inserted")) {
                    tv.setText("succesfully  Registered");
                    reg_emid.setText("");
                    reg_mob.setText("");
                    reg_pas.setText("");
                }
                if (result.equals("exist")) {
                    tv.setText("Sorry you are already registered");
                    reg_emid.setText("");
                    reg_mob.setText("");
                    reg_pas.setText("");
                }
            }

            @Override
            public void onFailure(Call<signp_response_model> call, Throwable t) {
                tv.setText("Something went wrong");
                tv.setTextColor(Color.RED);
                reg_emid.setText("");
                reg_mob.setText("");
                reg_pas.setText("");
            }

        });
    }
}