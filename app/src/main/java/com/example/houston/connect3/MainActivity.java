package com.example.houston.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    boolean gameIsActive = true;
    int active_player = 0;
    //2 means nnplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void restart(View view){
        TextView winnerMessage =  findViewById(R.id.winnerText);
        winnerMessage.setText("");
        active_player =0;
        for(int i=0; i<gameState.length; i++){
           gameState[i]=2;
        }
        android.support.v7.widget.GridLayout grid = findViewById(R.id.grid);
        for(int i=0; i<grid.getChildCount(); i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        gameIsActive = true;
    }
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameIsActive){
            gameState[tappedCounter] = active_player;
            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.blue_circle);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red_circle);
                active_player = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for(int[] winningPosition : winningPositions){
              if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                      && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                      && gameState[winningPosition[0]] !=2 ){
                  Toast toast = Toast.makeText(this,"WINNER",Toast.LENGTH_LONG);
                  toast.show();
                  TextView winnerMessage =  findViewById(R.id.winnerText);
                  String winner ="Red";
                  if( gameState[winningPosition[0]]==0){
                      winner ="Blue";
                  }
                  winnerMessage.setText("Player "+winner+ " Won!");
                  LinearLayout lin= findViewById(R.id.lin);
                  gameIsActive = false;
              }else{
                  boolean gameIsOver = true;
                  for(int counterState : gameState){
                      if(counterState==2) gameIsOver = false;
                  }
                  if(gameIsOver){
                      TextView winnerMessage =  findViewById(R.id.winnerText);
                      winnerMessage.setText("It's a draw .. Restarting match");
                      active_player =0;
                      for(int i=0; i<gameState.length; i++){
                          gameState[i]=2;
                      }
                      android.support.v7.widget.GridLayout grid = findViewById(R.id.grid);
                      for(int i=0; i<grid.getChildCount(); i++){
                          ((ImageView) grid.getChildAt(i)).setImageResource(0);
                      }
                      gameIsActive = true;
                  }
              }
            }
        }
    }
}
