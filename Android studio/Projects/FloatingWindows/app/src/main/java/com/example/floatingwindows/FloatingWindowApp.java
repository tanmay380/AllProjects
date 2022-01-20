package com.example.floatingwindows;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.transition.Hold;

public class FloatingWindowApp extends Service {

    private ViewGroup floatView;
    private WindowManager.LayoutParams floatWindowLayoutParams;
    public static int LAYOUT_TYPE;
    private WindowManager windowManager;

    private ImageButton btn;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("InflateParams")
    @Override
    public void onCreate() {
        super.onCreate();

        if(isServiceRunning())
            stopService(new Intent(FloatingWindowApp.this, holdingBox.class));

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        floatView = (ViewGroup) inflater.inflate(R.layout.floatingicon, null);

        btn = floatView.findViewById(R.id.image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else
            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_TOAST;

        floatWindowLayoutParams = new WindowManager.LayoutParams(
                100,100,
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        floatWindowLayoutParams.gravity = Gravity.CENTER;
        floatWindowLayoutParams.x = (int) ((int) (width*0.5));
        floatWindowLayoutParams.y = (int) ((int) (height*-0.35));
        windowManager.addView(floatView, floatWindowLayoutParams);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(FloatingWindowApp.this, holdingBox.class));
                stopSelf();
            }
        });

//        btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Toast.makeText(getApplicationContext(), "Floating Icon Removed", Toast.LENGTH_SHORT).show();
//                stopSelf();
//                windowManager.removeView(floatView);
//                return true;
//            }
//        });

        btn.setOnTouchListener(new View.OnTouchListener() {
            WindowManager.LayoutParams updated = floatWindowLayoutParams;
            double x = 0, y = 0, px = 0, py = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        x = (double) updated.x;
                        y = (double) updated.y;

                        px = motionEvent.getRawX();
                        py = motionEvent.getRawY();

                        Log.d("12345", "onTouch: " + "x: " + x + " y: " + y + "px: " + px + " py: " + py);

                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {

                        Log.d("12345", "onTouch: " + motionEvent.getRawX() + "  " + motionEvent.getRawY());
                        updated.x = (int) (x + motionEvent.getRawX() - px);
                        updated.y = (int) (y + motionEvent.getRawY() - py);

                        Log.d("12345", "onTouch: " + updated.x + "  " + updated.y);

                        windowManager.updateViewLayout(floatView, updated);
                        break;
                    }
                }
                return false;
            }
        });


    }
    public boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo act : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (FloatingWindowApp.class.getName().equals(act.service.getClassName()))
                return true;
        }
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        windowManager.removeView(floatView);
    }
}
