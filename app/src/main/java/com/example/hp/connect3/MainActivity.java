package com.example.hp.connect3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // yellow =0, red = 1
     int activePlayer = 0;
    boolean gameIsActive = true;
    int [] gameState = {2,2,2,2,2,2,2,2,2};//2 means unplayed
    int [][] winningPoistions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view)
    {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&& gameIsActive)
        {
            gameState[tappedCounter]= activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.black);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
            for(int []winPoistion : winningPoistions)
            {
              if(gameState[winPoistion[0]]==gameState[winPoistion[1]]&& gameState[winPoistion[1]]==gameState[winPoistion[2]]&&gameState[winPoistion[0]] != 2)
              {
                  // someone has won
                  gameIsActive = false;
                  String winner = "Red";
                  if(gameState[winPoistion[0]]==0){
                      winner = "Black";
                  }
                  TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                  winnerMessage.setText(winner + " has won!");
                  LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                  layout.setVisibility(View.VISIBLE);
              }
                else
              {
                  boolean gameIsOver = true;
                  for(int counterState: gameState)
                  {
                      if(counterState==2) gameIsOver = false;

                  }
                  if(gameIsOver)
                  {
                      TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                      winnerMessage.setText("Its a draw");
                      LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                      layout.setVisibility(View.VISIBLE);


                  }
              }


            }
        }

    }

    public void playAgain(View view)
    {
        gameIsActive= true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=0;

                for(int i=0; i< gameState.length;i++){
                    gameState[i]=2;
                }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}
