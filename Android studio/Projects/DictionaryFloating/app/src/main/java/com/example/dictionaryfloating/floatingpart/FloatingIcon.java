package com.example.dictionaryfloating.floatingpart;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.Image;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.dictionaryfloating.R;
import com.example.dictionaryfloating.backgroundservice.CheckFGApp;

public class FloatingIcon extends Service {
    private ViewGroup floatView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    public static int LAYOUT_TYPE;

    ImageButton btn;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(isServiceRunning()){
            stopService(new Intent(FloatingIcon.this,HoldingBox.class));
        }

        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        floatView = (ViewGroup) inflater.inflate(R.layout.floatingicon, null);

        btn = floatView.findViewById(R.id.flicon);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        else
            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_TOAST;


        layoutParams = new WindowManager.LayoutParams(
                80, 80,
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT
        );

        layoutParams.gravity = Gravity.CENTER;
        layoutParams.x = (int) ((int) (width * 0.5));
        layoutParams.y = (int) ((int) (height * -0.35));

        windowManager.addView(floatView, layoutParams);

        btn.setOnTouchListener(new View.OnTouchListener() {
            WindowManager.LayoutParams updated = layoutParams;
            double x = 0, y = 0, px = 0, py = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("12345", "onTouch: "  + "touching i float view");
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        x = (double) updated.x;
                        y = (double) updated.y;

                        px = motionEvent.getRawX();
                        py = motionEvent.getRawY();

                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        updated.x = (int) (x + motionEvent.getRawX() - px);
                        updated.y = (int) (y + motionEvent.getRawY() - py);

                        windowManager.updateViewLayout(floatView, updated);
                        break;
                    }
                }
                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(FloatingIcon.this, HoldingBox.class));
                stopSelf();
            }
        });
    }

    public boolean isServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo manager : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (FloatingIcon.class.getName().equals(manager.service.getClassName()))
                return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        CheckFGApp.isrunning = false;
        Log.d("12345", "onDestroy: " + "stopped");
        stopSelf();
        windowManager.removeView(floatView);
    }
}
