package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.model.Techology;

public class    TechologyAdapter extends ArrayAdapter<Techology> {

    private  Context context;
    private Techology[] techs;

    public TechologyAdapter(@NonNull Context context, @NonNull Techology[] techs) {
        super(context, R.layout.activity_techology, techs);
        this.context = context;
        this.techs = techs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.teach_item, null, true);
        ImageView img = view.findViewById(R.id.img);
        TextView name = view.findViewById(R.id.name);
        TextView sub = view.findViewById(R.id.sub);
        TextView desc = view.findViewById(R.id.desc);

        Techology  teach  = techs[position];
        img.setImageResource(teach.getImg());
        name.setText(teach.getName());
        sub.setText(teach.getSub());
        desc.setText(teach.getDesc());
        return view;
    }
}
