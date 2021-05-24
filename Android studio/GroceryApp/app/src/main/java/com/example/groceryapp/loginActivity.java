package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.groceryapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    private EditText InputNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog progressView;

    private String parentDbName="Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = findViewById(R.id.login_btn);
        InputNumber = findViewById(R.id.login_phone_number_input);
        InputPassword = findViewById(R.id.login_password_input_input);
        progressView = new ProgressDialog(this);

        LoginButton.setOnClickListener(v -> {
            LoginUser();
        });
    }

    private void LoginUser() {
        String phone = InputNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            InputNumber.setError("This Filed Cant Be Empty");
        } else if (TextUtils.isEmpty(password)) {
            InputPassword.setError("This Filed Cant Be Empty");

        }else{
            progressView.setTitle("LOGIN ACCOUNT");
            progressView.setTitle("Please wait, While we are checking your credentials");
            progressView.setCanceledOnTouchOutside(false);
            progressView.show();

            AllowAcces(phone, password);

        }
    }

    private void AllowAcces(String phone, String password) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phone).exists()) {
                    User userdata = snapshot.child(parentDbName).child(phone).getValue(User.class);

                    if (userdata.getPhone().equals(phone)) {
                        if (userdata.getPassword().equals(password)) {
                            Toast.makeText(loginActivity.this, "Log In Successfull", Toast.LENGTH_SHORT).show();
                            progressView.dismiss();

                            Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            progressView.dismiss();
                            InputPassword.setError("Password is Wrong");
                        }
                    }
                } else {
                        progressView.dismiss();
                        Toast.makeText(loginActivity.this, "Account with this " + phone + "does not exist", Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}