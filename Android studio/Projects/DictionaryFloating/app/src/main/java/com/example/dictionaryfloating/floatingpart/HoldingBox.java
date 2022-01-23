package com.example.dictionaryfloating.floatingpart;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.dictionaryfloating.R;

public class HoldingBox extends Service
{
    private ViewGroup floatview;
    private WindowManager.LayoutParams layoutParams;
    private WindowManager windowManager;

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
        int w= metrics.widthPixels;
        int h= metrics.heightPixels;

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        floatview  = (ViewGroup) inflater.inflate(R.layout.holdingbox, null);

        edt = floatview.findViewById(R.id.box_edt);
        listView = floatview.findViewById(R.id.listview);
        close = floatview.findViewById(R.id.imageView);

        layoutParams = new WindowManager.LayoutParams(
                (int) (w*0.45),(int) (w*0.45),
                FloatingIcon.LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        layoutParams.gravity = Gravity.CENTER;
        layoutParams.x = (int) (w*0.1);
        layoutParams.y = (int) (-h*0.5);

        windowManager.addView(floatview, layoutParams);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSelf();
                windowManager.removeView(floatview);
                Log.d("12345", "onClick: " + "closed");
                startService(new Intent(HoldingBox.this, FloatingIcon.class));
            }
        });

        edt.setOnTouchListener((view, motionEvent) -> {
            WindowManager.LayoutParams update = layoutParams;
            update.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            windowManager.updateViewLayout(floatview, update);
            return false;
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("12345", "onDestroy: " + "destroying holding box");
        stopSelf();
        windowManager.removeView(floatview);
    }
}
