package com.example.floatingwindows;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class holdingBox extends Service {
    private ViewGroup floatView;
    private WindowManager.LayoutParams layoutParams;
    private  WindowManager windowManager;

    EditText edt;
    ListView listView;
    ImageButton close;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        floatView = (ViewGroup) inflater.inflate(R.layout.holdingbox, null);

        edt = floatView.findViewById(R.id.edt_hold);
        listView = floatView.findViewById(R.id.lst_view);
        close = floatView.findViewById(R.id.fab);

        layoutParams = new WindowManager.LayoutParams(
                (int) (width*0.5),
                (int) (height*0.2),
                FloatingWindowApp.LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        layoutParams.gravity = Gravity.CENTER;
        layoutParams.x=0;
        layoutParams.y=(int) ((int) (height*-0.35));

        windowManager.addView(floatView, layoutParams);

        close.setOnClickListener(view -> {
            stopSelf();
            windowManager.removeView(floatView);
            Intent intent = new Intent(holdingBox.this, FloatingWindowApp.class);
            startService(intent);
        });

        edt.setOnTouchListener((view, motionEvent) -> {
            edt.setCursorVisible(true);
            WindowManager.LayoutParams floatWindowLayoutParamUpdateFlag = layoutParams;
            // Layout Flag is changed to FLAG_NOT_TOUCH_MODAL which
            // helps to take inputs inside floating window, but
            // while in EditText the back button won't work and
            // FLAG_LAYOUT_IN_SCREEN flag helps to keep the window
            // always over the keyboard
            floatWindowLayoutParamUpdateFlag.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

            // WindowManager is updated with the Updated Parameters
            windowManager.updateViewLayout(floatView, floatWindowLayoutParamUpdateFlag);
            return false;
        });

        floatView.setOnTouchListener(new View.OnTouchListener() {
            WindowManager.LayoutParams updated = layoutParams;
            double x = 0, y = 0, px = 0, py = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        x =  updated.x;
                        y =  updated.y;

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
}
