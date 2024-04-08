package com.example.myapplication9;

import androidx.annotation.Nullable;
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
    float dollar_rate=7.23F;
    float euro_rate =7.83F;
    float won_rate=0.0053F;
    public void Click1(View v){
           EditText j = findViewById(R.id.edit);
           TextView resultText = findViewById(R.id.text1);
            String str1 = j.getText().toString();
        if(str1.length()==0){
            Toast.makeText(this,"请输入金额后再计算",Toast.LENGTH_SHORT).show();
        }
        float m = Float.parseFloat(str1);
        m/=dollar_rate;
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
        m /= euro_rate;
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
        m /= won_rate;
        resultText.setText(String.valueOf("转换后的结果为"+m+"韩元"));
    }
    public void Click4(View v){
       //打开汇率窗口
        Intent ScoreIntent = new Intent(this,ConfigActivity.class);
        ScoreIntent.putExtra("dollar_rate_key",dollar_rate);
        ScoreIntent.putExtra("euro_rate_key", euro_rate);
        ScoreIntent.putExtra("won_rate_key",won_rate);
        startActivityForResult(ScoreIntent,6);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode==6 && resultCode==2){
            Bundle bdl2 =data.getExtras();
           dollar_rate=bdl2.getFloat("dollar_key2",0f);
            euro_rate =bdl2.getFloat("eruo_key2",0f);
            won_rate=bdl2.getFloat("won_key2",0f);}
        super.onActivityResult(requestCode,resultCode,data);
    }
}