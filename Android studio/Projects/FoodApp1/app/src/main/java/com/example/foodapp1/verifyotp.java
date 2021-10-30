package com.example.foodapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


import com.example.foodapp1.retrofitfiles.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class verifyotp extends AppCompatActivity {
    EditText otpenter;
    Button otpverify;
    private FirebaseAuth auth;
    String otpid;
    String email, pass, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);

        otpenter = findViewById(R.id.otpenter);
        otpverify = findViewById(R.id.otpid);

        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
         email = intent.getStringExtra("email");
         pass = intent.getStringExtra("pass");
         mobile = "+91"+intent.getStringExtra("mobile");

        Log.d("123456", "onCreate: " + email + "   " + pass + "  " + mobile);

        initiateotp(mobile);
        otpverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpenter.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Blank Field cannot be processed", Toast.LENGTH_SHORT).show();
                } else if (otpenter.getText().length() != 6)
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid, otpenter.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });


    }

    private void initiateotp(String mobile) {
        PhoneAuthProvider.verifyPhoneNumber(PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(mobile)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).build()
        );
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("1234", "signInWithCredential:success");
                            registerUser(email, mobile, pass);
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("12345", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "INvalid OTP entered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void registerUser(String email, String mobile, String pass) {
        String name="not applicable";
        String address="not applicable";
        View ll= findViewById(R.id.linearlayout);

        Call<signp_response_model> call = apicontroller.getInstance()
                                        .getapi()
                                        .getregister(name, email, pass,mobile,address);
        call.enqueue(new Callback<signp_response_model>() {
            @Override
            public void onResponse(Call<signp_response_model> call, Response<signp_response_model> response) {
                signp_response_model obj = response.body();
                String res= obj.getMessage().trim();

                if(res.equals("inserted"))
                {
                    Toast.makeText(getApplicationContext(), "Email id already exists", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString("username", email);
                    spe.putString("password", pass);
                    spe.commit();
                    spe.apply();
                    startActivity(new Intent(verifyotp.this, HomePage.class));
                    finish();
                }
                if(res.equals("exist")) {
                    Toast.makeText(getApplicationContext(), "Email id already exists", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(verifyotp.this, MainActivity.class));
                    finish();
                }
                if(res.equals("failed"))
                {
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
//                    Snackbar.make(ll,"Please try again", Snackbar.LENGTH_SHORT ).show();
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<signp_response_model> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                    onBackPressed();

            }
        });
    }
}