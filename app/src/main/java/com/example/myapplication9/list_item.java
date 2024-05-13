
        package com.example.myapplication9;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class list_item extends ListActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener,Runnable{
    private static final String TAG ="MylistActivity";
    private ArrayList<HashMap<String,String>> listItems;
    private SimpleAdapter listItemAdapter;
    private Handler handler;
    String text;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        // 获取保存的日期数据
        String updateDate = sharedPreferences.getString("update_date","");
        // 获取当前系统时间
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        if(!todayStr.equals(updateDate)){
            Log.i(TAG,"onCreate:需要更新");
            Thread t =new Thread(this);
            t.start();
        }else{
            Log.i(TAG,"onCreate:不需要更新");
        }

//setListAdapter(listItemAdapter);
        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    Log.i(TAG, "handleMessage:what="+msg.what );
                    listItems=(ArrayList<HashMap<String, String>>)msg.obj;
                    //listItemAdapter= new SimpleAdapter(list_item.this,listItems,
                    //           R.layout.activity_list_item,
                    //            new String[]{"itemTitle","itemDetail"},
                    //             new int[]{R.id.itemTitle,R.id.itemDetail});
                    //      setListAdapter(listItemAdapter);
                     myAdapter = new MyAdapter(list_item.this,R.layout.activity_list_item,listItems);
                    setListAdapter(myAdapter);

                    //创建数据库对象，写入数据
                    RateManager rateManager = new RateManager(list_item.this);
                    RateItem item = new RateItem();
                    item.setCname("币种");
                    item.setCval("hh");
                    rateManager.add(item);
                    Toast.makeText(list_item.this,"insert over",Toast.LENGTH_SHORT).show();

                }
                super.handleMessage(msg);
            }
        };
//定义一个线程获取数据
        Thread t=new Thread(this);
        t.start();
        //绑定事件处理
        getListView().setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String,String>map = (HashMap<String, String>) getListView().getItemAtPosition(position);
        Log.i(TAG,"onItemClick");
        String name= map.get("itemTitle");
        String rate= map.get("itemDetail");
        Log.i(TAG,"onItemClick:name="+name+"==>"+rate);
        //删除当前行
        myAdapter.remove(map);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG,"onItemLongClick:长按当前行");
        return true;
    }

    @Override
    public void run() {
        {
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
                RateManager rateManager = new RateManager(list_item.this);
                RateItem item = new RateItem();
                for(int i=0;i+2<message.length;i+=3){
                    String name = message[i+1];
                    String rate = message[i+2];
                    Log.i(TAG, "run:" + name + "==>" + rate);
                    HashMap<String,String> map = new HashMap<String,String>();
                    map.put("itemTitle",name);
                    map.put("itemDetail",rate);
                    item.setCname(name);
                    item.setCval(rate);
                    rateManager.add(item);
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
        }
    }
}
