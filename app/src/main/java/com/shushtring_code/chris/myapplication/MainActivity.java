package com.shushtring_code.chris.myapplication;

import android.drm.DrmStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import static com.shushtring_code.chris.myapplication.R.*;
import static com.shushtring_code.chris.myapplication.R.id.exitButton;
import static com.shushtring_code.chris.myapplication.R.id.playerTotal;
import static com.shushtring_code.chris.myapplication.R.id.theButton;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        final TextView firstTextView  = (TextView) findViewById(id.textview);
        final TextView buttonText     = (TextView) findViewById(theButton);
        final TextView exitButtonText = (TextView) findViewById(exitButton);
        final TextView playerScore    = (TextView) findViewById(playerTotal);
        final TextView dealerScore    = (TextView) findViewById(id.dealerTotal);


        Button button = (Button) findViewById(theButton);
        Button exitButton = (Button) findViewById(R.id.exitButton);
        final boolean[] playerDraws = new boolean[1];
        playerDraws[0]= true;

        exitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (playerDraws[0]) {
                    playerDraws[0] = false;
                    exitButtonText.setText("Not Drawing Cards");
                }
                else {
                    playerDraws[0] = true;
                    exitButtonText.setText("Drawing Cards");

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            //boolean buttonState = true;
            public Random rand = new Random();
            int dealerTotal = 0;
            int playerTotal = 0;
            boolean dealerDraws = true;
            int card;

            public String blackjackText(int playerTotal, int dealerTotal) {
                String displayText = "Let's Play Blackjack!";


                if (dealerTotal < 22) {

                    if ((this.playerTotal > 0) && (this.playerTotal < 10))
                        displayText = "Nice Start!";
                    else if ((this.playerTotal >= 10) && (this.playerTotal < 15))
                        displayText = "Halfway There!";
                    else if ((this.playerTotal >= 15) && (this.playerTotal < 21))
                        displayText = "Careful...";
                    else if ((this.playerTotal == 21))
                        displayText = "You Won! Hooray!";
                    else if (this.playerTotal > 21)
                        displayText = "Aw, You Lost...";
                    else
                        displayText = "Let's go!";

                    return displayText;
                }
                else if ((dealerTotal > 21) && (playerTotal > 21)) displayText = "Aw, You Lost...";
                else displayText = "You Won! Hooray!";

                return displayText;
            }
            @Override
            public void onClick(View v) {
                playerScore.setText("Current Score: " + playerTotal);
                dealerScore.setText("Dealer Score: " + dealerTotal);

                firstTextView.setText(blackjackText(playerTotal, dealerTotal));

                if (dealerTotal >= 15) {
                    if (rand.nextInt(10) < 2)
                        dealerDraws = false;
                } else if ((dealerTotal > 16) && (dealerTotal < 19)){
                    if (rand.nextInt(10) <= 5)
                        dealerDraws = false;
                } else if (dealerTotal == 19){
                    if (rand.nextInt(10) < 8)
                    dealerDraws = false;
                } else if (dealerTotal > 19) dealerDraws = false;


                if ((playerTotal >= 21) || (dealerTotal > 21)){
                    buttonText.setText("Restart");
                    playerTotal = 0;
                    dealerTotal = 0;
                    dealerDraws = true;
                }
                if (dealerDraws)  dealerTotal += rand.nextInt(9)+2;
                if (playerDraws[0]) playerTotal += rand.nextInt(9)+2;

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ButtonPress(View view) {

    }
}
