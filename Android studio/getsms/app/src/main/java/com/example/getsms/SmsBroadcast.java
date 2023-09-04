package com.example.getsms;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.getsms.roomdatabe.*;

public class SmsBroadcast extends BroadcastReceiver {
    public static final String TAG = "12345";
    public static String SMS = "android.provider.Telephony.SMS_RECEIVED";
    //    ArrayList<Model> list = MainActivity.list;
    String message;
    public static final String containsString = "to ANIL KUMAR on";

    Context mContext;

    // You have paid Rs.120.00 via a/c 91XX6219 to ANIL KUMAR on 17-08-2023. Ref:3533473512. Queries? Click http://m.paytm.me/care :PPBL
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        if (intent.getAction().equals(SMS)) {
            Bundle bundle = intent.getExtras();
            Object[] objects = (Object[]) bundle.get("pdus");

            if (objects != null) {
                for (Object aObject : objects) {
                    SmsMessage currentSMS = getIncomingMessage(aObject, bundle);
                    Log.d("12345", "onReceive: " + currentSMS.getOriginatingAddress());
                    //VD-BOBFSL
                    if (currentSMS.getOriginatingAddress().contains("PAYTM")) {
                        message = currentSMS.getDisplayMessageBody();
                        if (message.toLowerCase(Locale.ROOT).contains(containsString.toLowerCase(Locale.ROOT))) {
                            Toast.makeText(mContext, "SAVED IN THE APP", Toast.LENGTH_SHORT).show();
                            Pattern p = Pattern.compile("Rs.\\d*.\\d*");
                            Matcher m = p.matcher(message);
                            String sub = "";
                            while (m.find()) {
                                sub = m.group();
                            }
                            System.out.println("sub :->" + sub);
                            String[] ans = sub.split("is ");
                            String[] amount = ans[0].split("Rs.");
                            System.out.println("onReceive: " + amount[1]);
                            LocalDateTime time = LocalDateTime.now();

                            Pattern p1 = Pattern.compile("\\d*-\\d*-\\d*");
                            Matcher date = p1.matcher(message);
                            String dates = "";
                            while (date.find())
                                dates = date.group();

                            System.out.println(dates);
                            AppDatabase db = Room.databaseBuilder(context,
                                    AppDatabase.class, "Data_Store").allowMainThreadQueries().build();

                            UserDao dao = db.userDao();
                            dao.insert(new AmountInfo((int) Float.parseFloat(amount[1]), dates));
                            context.sendBroadcast(new Intent("MESSAGE_RECIEVED_UPDATE"));
                            makeNotification();
                        }
                    }
                }
            }
            this.abortBroadcast();

        } else {
            Toast.makeText(mContext, intent.getAction().toString(), Toast.LENGTH_LONG).show();
        }
    }


    private void makeNotification() {

        String CHANNEL_ID = "CHANNEL ID NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_money_24)
                .setContentTitle("Breakfast has been paid")
                .setContentText("Add people who are eating")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false);
        Intent intent = new Intent(mContext, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                .putExtra("notification", "openDialogue");


        PendingIntent pendingIntent = PendingIntent.getActivity(mContext,
                0, intent, PendingIntent.FLAG_MUTABLE);

        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = null;
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(CHANNEL_ID);

            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(CHANNEL_ID, "Add People", importance);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);

            }
        }
        notificationManager.notify(101, builder.build());
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
