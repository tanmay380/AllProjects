package com.example.getsms;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.getsms.roomdatabe.*;

public class Smsbroadcast extends BroadcastReceiver {
    public static String SMS = "android.provider.Telephony.SMS_RECEIVED";
    //    ArrayList<Model> list = MainActivity.list;
    String message;
    public static final String containsString = "is debited from kotak bank a/c";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS)) {
            Bundle bundle = intent.getExtras();
            Object[] objects = (Object[]) bundle.get("pdus");

            if (objects != null) {
                for (Object aObject : objects) {
                    SmsMessage currentSMS = getIncomingMessage(aObject, bundle);
                    message = currentSMS.getDisplayMessageBody();
                    if (message.toLowerCase(Locale.ROOT).contains(containsString.toLowerCase(Locale.ROOT))) {
                        Toast.makeText(context.getApplicationContext(), "SAVED IN THE APP", Toast.LENGTH_SHORT).show();
                        Pattern p = Pattern.compile("^Rs[.]\\d*[.]");
                        Matcher m = p.matcher(message);
                        String sub = "";
                        while (m.find()) {
                            sub = m.group();
                            System.out.println(m.group());
                        }
                        String[] ans = sub.split("Rs.");
                        System.out.println(ans[1]);
                        String[] finalstr = ans[1].split("\\.");
                        LocalDateTime time = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
                        String date = time.format(formatter);
                        AppDatabase db = Room.databaseBuilder(context,
                                AppDatabase.class, "Data_Store").allowMainThreadQueries().build();

                        UserDao dao = db.userDao();
                        dao.insert(new userInfo(finalstr[0], date));
                        openMoneyManager(context);
                    }
                }
            }
            this.abortBroadcast();

        } else {
            Toast.makeText(context.getApplicationContext(), intent.getAction().toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void openMoneyManager(Context context) {
        Log.d("12345", "openMoneyManager: " + "opening");
//        Intent intent = new Intent();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.realbyteapps.moneymanagerfree");
//        intent.setComponent(new ComponentName("com.realbyteapps.moneymanagerfree",
//                "com.realbyte.money.ui.inputUi.InputSaveContinue"));

        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT |
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(intent);

        Log.d("12345", "openMoneyManager: " + "opened");
    }

    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {
        SmsMessage currentSMS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);
        } else {
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }
}
