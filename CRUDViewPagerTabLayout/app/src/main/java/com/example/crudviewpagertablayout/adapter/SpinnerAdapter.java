package com.example.crudviewpagertablayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.crudviewpagertablayout.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs ;
//            = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};
    private Context context;

    public SpinnerAdapter( Context context,int[] imgs) {
        this.imgs = imgs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_spinner,viewGroup, false);
        ImageView imageView = item.findViewById(R.id.img);
        imageView.setImageResource(imgs[position]);
        return item;
    }
}
