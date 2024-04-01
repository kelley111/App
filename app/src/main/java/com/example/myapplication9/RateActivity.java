package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
    }
    public void Click1(View v){
           EditText j = findViewById(R.id.edit);
            TextView resultText = findViewById(R.id.text1);

            String str1 = j.getText().toString();
        if(str1.length()==0){
            Toast.makeText(this,"请输入金额后再计算",Toast.LENGTH_SHORT).show();
        }
            float m = Float.parseFloat(str1);
             m *= 0.14;
            resultText.setText(String.valueOf("转换后的结果为"+m+"美元"));
    }
    public void Click2(View v){
        EditText j = findViewById(R.id.edit);
        TextView resultText = findViewById(R.id.text1);

        String str1 = j.getText().toString();
        if(str1.length()==0){
            Toast.makeText(this,"请输入金额后再计算",Toast.LENGTH_SHORT).show();
        }

        float m = Float.parseFloat(str1);
        m *= 0.13;
        resultText.setText(String.valueOf("转换后的结果为"+m+"欧元"));
    }
    public void Click3(View v){
        EditText j = findViewById(R.id.edit);
        TextView resultText = findViewById(R.id.text1);
        String str1 = j.getText().toString();
        if(str1.length()==0){
            Toast.makeText(this,"请输入金额后再计算",Toast.LENGTH_SHORT).show();
        }
        float m = Float.parseFloat(str1);
        m *= 186.66;
        resultText.setText(String.valueOf("转换后的结果为"+m+"韩元"));
    }
    float dollarRate = 0.14F;
    float euroRate = 0.13F;
    float wonRate = 186.66F;

    public void Click4(View v){
        EditText j = findViewById(R.id.edit);
        TextView resultText = findViewById(R.id.text1);
        String str1 = j.getText().toString();

        Intent ScoreIntent = new Intent(this, MainActivity.class);
        ScoreIntent.putExtra("dollar_rate_key",dollarRate);
        ScoreIntent.putExtra("euro_rate_key",euroRate);
        ScoreIntent.putExtra("won_rate_key",wonRate);

        startActivity(ScoreIntent);

    }


}