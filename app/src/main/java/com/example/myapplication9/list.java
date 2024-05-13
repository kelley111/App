package com.example.myapplication9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class list extends ListActivity {

    private Context listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //准备数据
        ArrayList<HashMap<String,String>> listItems = new ArrayList<HashMap<String,String>>();
        for(int i=0 ;i<20 ; i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("itemTitle","Rate:"+i);
            map.put("itemDetail","detail"+i);
            listItems.add(map);
        }
//生成适配器
        MyAdapter myAdapter = new MyAdapter(this,R.layout.list,listItems);
        this.setListAdapter(myAdapter);
    }
}