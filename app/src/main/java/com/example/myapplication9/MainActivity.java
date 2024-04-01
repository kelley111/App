package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int m=0;
    public void onClick3(View v){
        m= m +3;
        TextView show1 =findViewById(R.id.txtout1);
        show1.setText(String.valueOf(m));

    }
    public void onClick2(View v){
        m= m +2;
        TextView show1 =findViewById(R.id.txtout1);
        show1.setText(String.valueOf(m));

    }
    public void onClick1(View v){
        m= m +1;
        TextView show1 =findViewById(R.id.txtout1);
        show1.setText(String.valueOf(m));

    }

    public void onClick0(View v){
        m=0;
        TextView show1 =findViewById(R.id.txtout1);
        show1.setText(String.valueOf(m));

    }
   Intent intent =getIntent();



}