package com.example.myapplication.infront;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.background.Animator;
import com.example.myapplication.background.alphabetBaseShow;

import java.util.Random;

import info.hoang8f.widget.FButton;

public class CharacterSelection extends Activity {

    private int Colors[] = {R.color.Green,
            R.color.Red,
            R.color.Orange,
            R.color.Pink};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_selection);
        LinearLayout root = (LinearLayout) findViewById(R.id.CharacterLL);
        LinearLayout child = new LinearLayout(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int horizontal_padding = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int vertical_padding = (int) getResources().getDimension(R.dimen.activity_vertical_margin);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((size.x - 8 * horizontal_padding) / 4, (size.x - 8 * horizontal_padding) / 4);
        params.setMargins(horizontal_padding, vertical_padding, horizontal_padding, vertical_padding);


        int count = 0;
        for (int i = 0; i < SplashScreen.CHARACTER_LIST.length; i++) {
            String s = SplashScreen.CHARACTER_LIST[i];


            Animation animation = Animator.createScaleUpCompleteAnimation();
                    animation.setStartOffset(20 * i);


            final FButton fb = new FButton(this);
            fb.startAnimation(animation);
            fb.setText(s);
            fb.setTextSize(((size.x - 8 * horizontal_padding) / 4) * 1.0f / 4.0f);
            fb.setAllCaps(false);
            //  fb.setButtonColor(getResources().getColor(colours[new Random().nextInt(colours.length)])); //setting a random colour
            fb.setButtonColor(getColor(Colors[new Random().nextInt(Colors.length)]));
            fb.setShadowEnabled(false);
            fb.setCornerRadius((size.x - 8 * horizontal_padding) / 4); //radius to fit 4 buttons on the screen
            fb.setLayoutParams(params);
           fb.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent;
                   intent = new Intent(CharacterSelection.this, alphabetshow.class);
                   intent.putExtra(getResources().getString(R.string.practice_string),((Button)v).getText());
                   startActivity(intent);

               }
           });


            child.addView(fb);

            count++;
            if (count == 4) {
                root.addView(child);
                child = new LinearLayout(this);
                count = 0;
            }
        }

        root.addView(child);
    }
}



