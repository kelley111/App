package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
public void click1(View v){
        if(v.getId()==R.id.s3){
            TextView scoreText=findViewById(R.id.scores1);
            int s1=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(s1+3));
        } else if (v.getId()==R.id.s2) {
                TextView scoreText=findViewById(R.id.scores1);
                int s1=Integer.parseInt(scoreText.getText().toString());
                scoreText.setText(String.valueOf(s1+2));}
        else if (v.getId()==R.id.s1) {
            TextView scoreText=findViewById(R.id.scores1);
            int s1=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(s1+1));}}
    public void click2(View v){
        if(v.getId()==R.id.s4){
            TextView scoreText=findViewById(R.id.scores2);
            int s1=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(s1+3));
        } else if (v.getId()==R.id.s5) {
            TextView scoreText=findViewById(R.id.scores2);
            int s1=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(s1+2));
        }
        else if (v.getId()==R.id.s6) {
            TextView scoreText=findViewById(R.id.scores2);
            int s1=Integer.parseInt(scoreText.getText().toString());
            scoreText.setText(String.valueOf(s1+1));
        }
    }
  public void click3(View v){
      TextView scoreText=findViewById(R.id.scores1);
      int s1=Integer.parseInt(scoreText.getText().toString());
      s1=0;
      scoreText.setText(String.valueOf(s1));
      TextView scoreText1=findViewById(R.id.scores2);
      int s2=Integer.parseInt(scoreText1.getText().toString());
      s2=0;
      scoreText1.setText(String.valueOf(s2));
  }
}