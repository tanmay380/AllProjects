package com.example.foodapp1.retrofitfiles;

public class signin_response_model {
    String message;

    public signin_response_model() {
    }

    public signin_response_model(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
