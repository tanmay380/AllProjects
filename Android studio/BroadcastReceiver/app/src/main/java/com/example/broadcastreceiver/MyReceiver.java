package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("12345", "onReceive: " + "triggere");
        if(intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED")) {
            Log.d("12345", "onReceive: " + "triggere");
        }
    }
}