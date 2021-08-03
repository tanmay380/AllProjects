package com.example.foodapp.retrofit;

public class signUp_response_model {
    String mesaage;

    public signUp_response_model(String message) {
        this.mesaage = message;
    }

    public signUp_response_model() {
    }

    public String getMessage() {
        return mesaage;
    }

}
