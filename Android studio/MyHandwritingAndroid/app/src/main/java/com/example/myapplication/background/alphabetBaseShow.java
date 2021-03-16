package com.example.myapplication.background;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.infront.CharacterSelection;
import com.example.myapplication.infront.SplashScreen;
import com.software.shell.fab.ActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class alphabetBaseShow extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    protected String mPracticeString;
    protected DrawingView mDrawView;
    public SeekBar seekBar;
    Button button;
    public boolean draw = true;
    AudioManager audio;
    protected TextView mScoreTimerView;
    protected TextView mBestScoreView;
    protected boolean mDone;
    public AlertDialog.Builder alertDialog;
    private static final int STORAGE_PERMISSION_CODE = 101;
    public TextView colorset;
    public NavigationView navigationView;
    //private TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            System.gc();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_alphabet_show);

            Toolbar toolbar = findViewById(R.id.PracticeToolbar);
            setSupportActionBar(toolbar);
            // textToSpeech=new TextToSpeech(this,LanguageSelection);
            drawerLayout = findViewById(R.id.drawerLayout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.close, R.string.open);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            mDrawView = new DrawingView(this);
            seekBar = findViewById(R.id.size1);
            mDrawView = findViewById(R.id.DrawingView);
            mBestScoreView = (TextView) findViewById(R.id.best_score_View);
            mScoreTimerView = (TextView) findViewById(R.id.score_and_timer_View);
            mPracticeString = getIntent().getStringExtra(getResources().getString(R.string.practice_string));
            mDone = false;
            mScoreTimerView.bringToFront();

            mDrawView.canVibrate(true);
            mDrawView.setBitmapFromText(mPracticeString);

            audio = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        /*switch (audio.getStreamVolume(AudioManager.STREAM_MUSIC)) {
            case AudioManager.RINGER_MODE_SILENT:
                Toast.makeText(getApplicationContext(), "TURN UP THE VOLUME", Toast.LENGTH_SHORT).show();
        }*/
            if ((audio.getStreamVolume(AudioManager.STREAM_MUSIC) > 1)) {
                SplashScreen.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
            } else if (audio.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, 10, 1);

                SplashScreen.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        } catch (Exception e) {
            showErrorDialog(e);
        }

    }


    private void toggleSeekbar() {
        if (seekBar.getVisibility() == View.INVISIBLE) {
            seekBar.setVisibility(View.VISIBLE);
            seekBar.bringToFront();
            mDrawView.candraw(false);
        } else {
            seekBar.setVisibility(View.INVISIBLE);
            mDrawView.candraw(true);
        }

    }

    public void clickit() {
        toggleSeekbar();
        setSize();
    }


    public void setSize() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDrawView.setCurrentWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                toggleSeekbar();
            }
        });
        return;
    }

    public void practiceOnClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                if (mDone) {
                    mDrawView.init();
                }

                mDrawView.setBitmapFromText(mPracticeString);
                mBestScoreView.setAnimation(Animator.createFadeOutAnimation());
                mDrawView.startAnimation(Animator.createScaleUpAnimation());
                mScoreTimerView.setAnimation(Animator.createFadeOutAnimation());

                Animator.createYFlipForwardAnimation(findViewById(R.id.done));
                ((ActionButton) findViewById(R.id.done)).setImageResource(R.drawable.ic_done);
                Animator.createYFlipBackwardAnimation(findViewById(R.id.done));
                mDone = false; //implies that the user isn't done
                mDrawView.candraw(true);

                break;

            case R.id.done:
                String result = mDrawView.saveBitmap(mPracticeString, "");
                if (result.charAt(0) == '/')
                    result = "Trace Saved";

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();//Toast displayed with the status of saving the trace
                if (!mDone) {
                    float best = SplashScreen.mDbHelper.getScore(mPracticeString);
                    if (best < mDrawView.score()) {
                        best = mDrawView.score();
                        SplashScreen.mDbHelper.writeScore(mPracticeString, best);
                    }

                    //Animations for when the user is done with the trace
                    mDrawView.startAnimation(Animator.createScaleDownAnimation());
                    findViewById(R.id.best_score_View).bringToFront();
                    ((TextView) findViewById(R.id.best_score_View)).setText("Best: " + String.valueOf(best));
                    mScoreTimerView.setText("Score: " + String.valueOf(mDrawView.score()));
                    mScoreTimerView.setAnimation(Animator.createFadeInAnimation());
                    mBestScoreView.setAnimation(Animator.createFadeInAnimation());

                    Animator.createYFlipForwardAnimation(findViewById(R.id.done));
                    ((ActionButton) findViewById(R.id.done)).setImageResource(R.drawable.ic_save);
                    Animator.createYFlipBackwardAnimation(findViewById(R.id.done));

                    mDrawView.candraw(false);
                    mDone = true;

                }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (!mDone) {
            int next = Arrays.asList(SplashScreen.CHARACTER_LIST).indexOf(mPracticeString);
            switch (item.getItemId()) {
                case R.id.action_next:
                    next = (next + 1) % SplashScreen.CHARACTER_LIST.length;
                    break;
                case R.id.action_previous:
                    next = (next + SplashScreen.CHARACTER_LIST.length - 1) % SplashScreen.CHARACTER_LIST.length;
                    break;
                case R.id.setting:
                    clickit();
                    break;


            }
            if (item.getItemId() == R.id.action_next || item.getItemId() == R.id.action_previous) {
                mPracticeString = SplashScreen.CHARACTER_LIST[next];
                mDrawView.setBitmapFromText(mPracticeString);
                SplashScreen.TTSobj.speak(mPracticeString, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    public void colorclick(View v) {
//        switch (v.getId()) {
//            case R.id.blue:
//                mDrawView.setcolor(Color.BLUE);
//                break;
//            case R.id.ora:
//                mDrawView.setcolor(Color.rgb(255, 165, 0));
//                break;
//            case R.id.green:
//                mDrawView.setcolor(Color.GREEN);
//                break;
//            case R.id.yelllow:
//                mDrawView.setcolor(Color.YELLOW);
//                break;
//            case R.id.brown:
//                mDrawView.setcolor(Color.rgb(185, 122, 87));
//                break;
//            case R.id.grey:
//                mDrawView.setcolor(Color.GRAY);
//                break;
//        }
//    }


    protected void showErrorDialog(final Exception e) {
        new AlertDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage(Log.getStackTraceString(e))
                .setPositiveButton("Save to File", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + getResources().getString(R.string.app_name));
                            File file = new File(mediaStorageDir.getPath() + File.separator + "error.txt");
                            if (file.exists() || file.createNewFile()) {
                                FileOutputStream fOut = new FileOutputStream(file, true);
                                fOut.write(("\n\n" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date()) + "\n\n").getBytes());
                                fOut.write(Log.getStackTraceString(e).getBytes());
                                fOut.flush();
                                fOut.close();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(alphabetBaseShow.this, CharacterSelection.class));
        super.onBackPressed();
    }



}
