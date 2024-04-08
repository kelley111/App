package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {
    EditText dollarEdit, euroEdit, wonEdit;

    protected void save(View btn) {
        //初始化
        euroEdit=findViewById(R.id.euro_rate);
        dollarEdit=findViewById(R.id.dollar_rate);
        wonEdit=findViewById(R.id.won_rate);
        //保存数据
        float new_dollar = Float.parseFloat(dollarEdit.getText().toString());
        float new_euro = Float.parseFloat(euroEdit.getText().toString());
        float new_won = Float.parseFloat(wonEdit.getText().toString());
        //带回数据
        Intent retIntent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("dollar_key2", new_dollar);
        bdl.putFloat("euro_key2", new_euro);
        bdl.putFloat("won_key2", new_won);
        retIntent.putExtras(bdl);
        setResult(2, retIntent);
        finish();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //初始化变量
        dollarEdit = findViewById(R.id.dollar_rate);
        euroEdit = findViewById(R.id.euro_rate);
        wonEdit = findViewById(R.id.won_rate);
        //接收传入的汇率数据
        Intent intent = getIntent();
        float dollar1 = intent.getFloatExtra("dollar_rate_key", 0f);
        float euro1 = intent.getFloatExtra("euro_rate_key", 0f);
        float won1 = intent.getFloatExtra("won_rate_key", 0f);
        //显示到控件中
        dollarEdit.setText(String.valueOf(dollar1));
        euroEdit.setText(String.valueOf(euro1));
        wonEdit.setText(String.valueOf(won1));
    }
}