
        package com.example.myapplication9;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class list_item extends ListActivity {
    private static final String TAG ="MylistActivity";
    private ArrayList<HashMap<String,String>> listItems;
    private SimpleAdapter listItemAdapter;
    private Handler handler;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//setListAdapter(listItemAdapter);
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    Log.i(TAG, "handleMessage:what="+msg.what );
                    listItems=(ArrayList<HashMap<String, String>>)msg.obj;
                    listItemAdapter= new SimpleAdapter(list_item.this,listItems,
                            R.layout.activity_list_item,
                            new String[]{"itemTitle","itemDetail"},
                            new int[]{R.id.itemTitle,R.id.itemDetail});
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };
//定义一个线程获取数据
        Thread t=new Thread(()->{
            ArrayList<HashMap<String,String>> list= new ArrayList<HashMap<String,String>>();
            int id=0;
            try {
                Document doc = Jsoup.connect("https://www.huilvzaixian.com").get();
                Elements tables = doc.getElementsByTag("ul");
                for (Element ulElement : tables) {
                    Elements liElements = ulElement.select("li");
                    for(Element liElement : liElements){
                        if(id!=0) break;
                        text=liElement.text();
                        id=1;
                    }
                }
                String[] message = text.split("\\s");

                for(int i=0;i+2<message.length;i+=3){
                    String name = message[i+1];
                    String rate = message[i+2];
                    Log.i(TAG, "run:" + name + "==>" + rate);
                    HashMap<String,String> map = new HashMap<String,String>();
                    map.put("itemTitle",name);
                    map.put("itemDetail",rate);
                    list.add(map);
                }
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
//获取MSG对象，用于返回主线程
            Message msg = handler.obtainMessage(7,list);
            handler.sendMessage(msg);
        });
        t.start();
    }


}
