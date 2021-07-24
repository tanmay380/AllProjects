package com.example.broadcastreceiver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){
            Toast.makeText(context,"Chargin", Toast.LENGTH_SHORT).show();
        }
        if(Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())){
            Toast.makeText(context,"disconencted", Toast.LENGTH_SHORT).show();
        }
    }
}
