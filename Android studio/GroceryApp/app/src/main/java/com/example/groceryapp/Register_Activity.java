package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.ProgressView;

import java.util.HashMap;

public class Register_Activity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);


        CreateAccountButton= findViewById(R.id.register_btn);
        InputName= findViewById(R.id.register_user_name_input);
        InputPhoneNumber= findViewById(R.id.register_phone_number_input);
        InputPassword= findViewById(R.id.register_password_input);
        progressView= new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(v -> {
            CreateAccount();
        });

    }
    private void CreateAccount(){
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            InputName.setError("This Filed Cant Be Empty");
        } else if (TextUtils.isEmpty(phone)){
            InputPhoneNumber.setError("This Filed Cant Be Empty");
        }else if (TextUtils.isEmpty(password)){
            InputPassword.setError("This Filed Cant Be Empty");
        }else{
            progressView.setTitle("CREATE ACCOUNT");
            progressView.setTitle("Please wait, While we are creating your account");
            progressView.setCanceledOnTouchOutside(false);
            progressView.show();

            ValidatePhoneNumber(name, phone, password);

        }

    }

    private void ValidatePhoneNumber(String name, String phone, String password) {
            final DatabaseReference RootRef;
            RootRef= FirebaseDatabase.getInstance().getReference();

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!(snapshot.child("Users").child(phone).exists())){
                        HashMap<String, Object> userdataMap = new HashMap<>();
                        userdataMap.put("phone", phone);
                        userdataMap.put("password", password);
                        userdataMap.put("name", name);

                        RootRef.child("Users").child(phone).updateChildren(userdataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(Register_Activity.this, "Your account created successfully " , Toast.LENGTH_SHORT).show();
                                            progressView.dismiss();

                                            Intent intent= new Intent(Register_Activity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(Register_Activity.this, "Network Error, please try again " , Toast.LENGTH_SHORT).show();
                                            progressView.dismiss();
                                        }
                                    }
                                });
                    }else{
                        Toast.makeText(Register_Activity.this, "This " + phone +"already exists" , Toast.LENGTH_SHORT).show();
                        progressView.dismiss();
                        Intent intent= new Intent(Register_Activity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }
}