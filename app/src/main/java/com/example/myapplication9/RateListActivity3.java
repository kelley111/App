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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RateListActivity3 extends ListActivity implements Runnable {

    private static final String TAG ="MylistActivity";
    private SimpleAdapter listItemAdapter;

    Handler handler;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list3);
        //准备数据
        ArrayList<HashMap<String,String>> listItems = new ArrayList<HashMap<String,String>>();
        for(int i=0 ;i<20 ; i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("itemTitle","Rate:"+i);
            map.put("itemDetail","detail"+i);
            listItems.add(map);
        }

        //准备适配器
        SimpleAdapter listItemAdapter  = new SimpleAdapter(this,listItems,R.layout.activity_list_item,new String[]{"ItemTitle","ItemDetail"},new int[]{R.id.itemTitle,R.id.itemDetail});
        ListView mylistview = findViewById(R.id.mylist);
        TextView nodata = findViewById(R.id.nodata);
        mylistview.setAdapter(listItemAdapter);
       mylistview.setEmptyView(nodata);
        ProgressBar bar = findViewById(R.id.progressbar);

        handler = new Handler(Looper.myLooper()){
              public void handleMessage(Message msg){
                  if(msg.what == 5){
                      Toast.makeText(RateListActivity3.this,"数据已更新",Toast.LENGTH_SHORT).show();
                      ArrayList<HashMap<String,String>>list2 = (ArrayList<HashMap<String, String>>)msg.obj;
                      MyAdapter myAdapter = new MyAdapter(RateListActivity3.this,R.layout.activity_list_item,list2);
                      mylistview.setAdapter(myAdapter);
                      mylistview.setVisibility(View.VISIBLE);
                      bar.setVisibility(View.GONE);
                  }
              }
        };
    }

    //定义一个线程获取数据
    @Override
    public void run() {
        Log.i(TAG, "run()........");
        List<String> rateList =new ArrayList<String>();
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