package com.example.myapplication9;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class RateActivity extends AppCompatActivity implements Runnable {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        //启动线程
        Thread t = new Thread(this);
        t.start();
        Log.i(TAG,"onCreate:t.start().......");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg){
                Log.i(TAG,"handleMessage:收到消息：+.....");
                if(msg.what==6){
                    Log.i(TAG,"handleMessage:what="+msg.what);
                    Toast.makeText(RateActivity.this,"收到："+msg.obj,Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
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
    @Override
    public void run() {
         Log.i(TAG, "run()........");

        //获取网络数据
        URL url = null;
        try {
            url =new URL("https://www.boc.cn/sourcedb/whpj/");
            HttpURLConnection http =(HttpURLConnection)url.openConnection();
            InputStream in = http.getInputStream();
            String html = inputStream2String(in);
            Log.i(TAG,"run:html="+html);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

         //发送数据回主线程
        Message msg = handler.obtainMessage(6);
        msg.obj = "hello Android";
        handler.sendMessage(msg);
        Log.i(TAG,"run:msg已发送");


    }
    private String  inputStream2String(InputStream inputStream)throws IOException{
        final int bufferSize= 1024;
        final char[]buffer =new char[bufferSize];
        final StringBuilder out =new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"utf-8");
        while (true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer,0,rsz);
        }
        return out.toString();
    }


}