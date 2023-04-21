package com.example.crud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.crud.R;

public class AdapterSpinner extends BaseAdapter {
    private Context context;
    int[] imgs;

    public AdapterSpinner(Context context, int[] imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.item_img,viewGroup,false);
        ImageView img  = v.findViewById(R.id.img);
        img.setImageResource(imgs[i]);
        System.out.println("VIEW  "+ view);
        return v;
    }
    //
}
