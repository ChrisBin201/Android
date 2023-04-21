package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> personList;

    public StudentAdapter(Context context, List<Student> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_person, parent, false);

        TextView nameTextView = convertView.findViewById(R.id.name_text_view);
        TextView ageTextView = convertView.findViewById(R.id.maSV_text_view);

        Student student = (Student) getItem(position);

        nameTextView.setText(student.getName());
        ageTextView.setText("mã sinh viên: " + student.getMaSV());

        return convertView;
    }
}
