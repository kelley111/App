package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

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
}