package com.example.myapplication9;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends ArrayAdapter{
    private static final String TAG="MyAdapter";
    public MyAdapter(Context context, int resource , ArrayList<HashMap<String,String>>list){
        super(context,resource,list);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;
        if(itemView == null){
            itemView= LayoutInflater.from(getContext()).inflate(R.layout.list,parent,false);
        }
        Map<String,String> map =(Map<String, String>)getItem(position);
        TextView title =(TextView)itemView.findViewById(R.id.itemTitle);
        TextView detail =(TextView)itemView.findViewById(R.id.itemDetail);

        title.setText("Title:"+map.get("itemTitle"));
        detail.setText("Detail:"+map.get("itemDetail"));

        //根据汇率设置背景颜色
        Resources.Theme theme =getContext().getTheme();
        int myCustomColor = getContext().getResources().getColor(R.color.my_light_primary,theme);
        int defaultColor = ContextCompat.getColor(getContext(),R.color.white);
        itemView.setBackgroundColor(defaultColor);
        try {
            float rate = Float.parseFloat(map.get("itemDetail"));
            float redIntensity =(float) rate/900f;
            int redColor = Color.argb((int)(255 * redIntensity),255,0,0);
            itemView.setBackgroundColor(redColor);
        }catch (NumberFormatException ex){
            Log.e(TAG,"getView:"+ex);
        }

        return itemView;
    }

}
