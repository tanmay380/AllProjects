package com.example.foodapp1.retrofitfiles;

public class signp_response_model
{
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public signp_response_model() {
    }

    public signp_response_model(String message) {
        this.message = message;
    }
}
