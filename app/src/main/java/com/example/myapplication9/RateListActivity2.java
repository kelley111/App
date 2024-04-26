package com.example.myapplication9;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class RateListActivity2 extends ListActivity implements Runnable {//父类必须为ListActivity
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //准备数据
        // String[]data={"one","two","three","four"};
        // List<String> list1 =new ArrayList<String>();
        // for(int i=1;i<100;i++){
        //    list1.add("item"+i);
        //}
        //适配器
        //ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
        //simple..是标记每一行的布局,不表示页面内容
        // setListAdapter(adapter);
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg){
                Log.i(TAG,"handleMessage:收到消息：+.....");
                if(msg.what==5) {
                    List<String> list2=(List<String>)msg.obj;
                    ListAdapter adapter =new ArrayAdapter<String>(RateListActivity2.this,android.R.layout.simple_list_item_1,list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        //启动线程
        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        Log.i(TAG, "run()........");
        List<String>rateList =new ArrayList<String>();
        //获取网络数据
        try {
            Document doc = Jsoup.connect( "https://www.boc.cn/sourcedb/whpj/").get();
            Elements tables = doc.getElementsByTag(  "table");
            Element table2 =tables.get(1);
            Log.i(TAG, "run:table2"+table2);
            Elements trs =table2.getElementsByTag( "tr");
            for(Element tr:trs){
                Elements tds =tr.getElementsByTag( "td");if(tds.size()>6){
                    String rname = tds.get(0).text();
                    String rateStr = tds.get(5).text();
                    rateList.add(rname+"=>"+rateStr);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();}
        //发送数据回主线程
        Message msg = handler.obtainMessage(5);
        msg.obj = rateList;
        handler.sendMessage(msg);
    }
}



