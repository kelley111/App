package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    Integer m=0;
    Integer n=0;
    protected void onSaveInstanceState(Bundle outstate){
     super.onSaveInstanceState(outstate);
     outstate.putInt("team_score1",m);
     outstate.putInt("team_score2",n);
    }

    protected void onRestoreInstanceState(Bundle SaveInstanceState){
        super.onRestoreInstanceState(SaveInstanceState);
        m = SaveInstanceState.getInt("team_score1");
        n = SaveInstanceState.getInt("team_score2");
        TextView scoreText=findViewById(R.id.scores1);
        TextView scoreText2=findViewById(R.id.scores2);
        scoreText.setText(String.valueOf(m));
        scoreText2.setText(String.valueOf(n));
    }
public void click1(View v){
        if(v.getId()==R.id.s3){
            TextView scoreText=findViewById(R.id.scores1);
            int m=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(m+3));
        } else if (v.getId()==R.id.s2) {
                TextView scoreText=findViewById(R.id.scores1);
                int m=Integer.parseInt(scoreText.getText().toString());
                scoreText.setText(String.valueOf(m+2));}
        else if (v.getId()==R.id.s1) {
            TextView scoreText=findViewById(R.id.scores1);
            int m=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(m+1));}}
    public void click2(View v){
        if(v.getId()==R.id.s4){
            TextView scoreText=findViewById(R.id.scores2);
            int n=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(n+3));
        } else if (v.getId()==R.id.s5) {
            TextView scoreText=findViewById(R.id.scores2);
            int n=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(n+2));
        }
        else if (v.getId()==R.id.s6) {
            TextView scoreText=findViewById(R.id.scores2);
            int n=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(n+1));
        }
    }


  public void click3(View v){
      TextView scoreText=findViewById(R.id.scores1);
      int m=Integer.parseInt(scoreText.getText().toString());
      m=0;
      scoreText.setText(String.valueOf(m));
      TextView scoreText1=findViewById(R.id.scores2);
      int n=Integer.parseInt(scoreText1.getText().toString());
      n=0;
      scoreText1.setText(String.valueOf(n));

  }
}