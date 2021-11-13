package com.example.attendanceapp.retrofit;

public class signup_response_model {
    String message;

    public signup_response_model(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
