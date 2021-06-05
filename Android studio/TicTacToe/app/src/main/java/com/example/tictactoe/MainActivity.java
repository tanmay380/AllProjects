package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0- 0
    //0- X

    boolean gameactive = true;

    int activePlayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    /* State Meanings
    0 - X
    1 - O
    2 - NULL
     */
    int[][] gamewinnings = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void PlayerClick(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameactive) {
            gameReset();
        }

        if (gamestate[tappedImage] == 2 && gameactive) {
            gamestate[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O'S TURN - TAP TO PLAY");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X'S TURN - TAP TO PLAY");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int emptysapce: gamestate){
            if(emptysapce==2){
                Toast.makeText(getApplicationContext(), "HELLOQ", Toast.LENGTH_SHORT).show();
            }
        }

        for (int[] winposition : gamewinnings) {
            if (gamestate[winposition[0]] == gamestate[winposition[1]] &&
                    gamestate[winposition[1]] == gamestate[winposition[2]] &&
                    gamestate[winposition[0]] != 2) {
                String winnerStr;
                gameactive = false;
                if (gamestate[winposition[0]] == 0) {
                    winnerStr = "X HAS WON";
                } else {
                    winnerStr = "O HAS WON";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
        }
    }

    private void gameReset() {
        gameactive = true;
        activePlayer = 0;
        Arrays.fill(gamestate, 2);
        for (int i = 0; i < gamestate.length; i++) {
            ((ImageView) findViewById(getResources().getIdentifier("imageView" + i, "id", this.getPackageName())))
                    .setImageResource(0);
        }
    }
}