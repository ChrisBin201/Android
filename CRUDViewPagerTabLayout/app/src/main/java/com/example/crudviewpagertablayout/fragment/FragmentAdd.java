package com.example.crudviewpagertablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudviewpagertablayout.MainActivity;
import com.example.crudviewpagertablayout.R;
import com.example.crudviewpagertablayout.adapter.CatAdapter;
import com.example.crudviewpagertablayout.adapter.SpinnerAdapter;
import com.example.crudviewpagertablayout.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener {
    private CatAdapter catAdapter;
    private Spinner spinner;
    private EditText editName, editPrice, editInfo;
    private Button btAdd,btUpdate;
    private RecyclerView recyclerView;
    private int pcurr;
    private int[] imgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        catAdapter = new CatAdapter((MainActivity) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(catAdapter);
        catAdapter.setItemListener(this);
        btAdd.setOnClickListener(view1 -> {
            String i = spinner.getSelectedItem().toString();
            int img;
            try{
                img = imgs[Integer.parseInt(i)];
                double price = Double.parseDouble(editPrice.getText().toString());
                Cat cat = new Cat(img,editName.getText().toString(),editInfo.getText().toString(),price);
                catAdapter.add(cat);

            } catch (NumberFormatException e) {

            }
        });
        btUpdate.setOnClickListener(view1 -> {
            String i = spinner.getSelectedItem().toString();
            int img;
            try{
                img = imgs[Integer.parseInt(i)];
                double price = Double.parseDouble(editPrice.getText().toString());
                Cat cat = new Cat(img,editName.getText().toString(),editInfo.getText().toString(),price);
                catAdapter.update(pcurr,cat);
                btAdd.setVisibility(View.VISIBLE);
                btUpdate.setVisibility(View.INVISIBLE);
            } catch (NumberFormatException e) {

            }
        });
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(),imgs);
        spinner.setAdapter(adapter);
        editName = view.findViewById(R.id.editName);
        editPrice = view.findViewById(R.id.editPrice);
        editInfo = view.findViewById(R.id.editInfo);
        btAdd = view.findViewById(R.id.btAdd);
        btUpdate = view.findViewById(R.id.btUpdate);
        recyclerView = view.findViewById(R.id.rView);
        btUpdate.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setVisibility(View.INVISIBLE);
        btUpdate.setVisibility(View.VISIBLE);
        pcurr = position;
        Cat cat = catAdapter.getItem(position);
        int img = cat.getImage();
        int p=0;
        for (int i = 0; i < imgs.length; i++) {
            if(imgs[i]==img){
                p=i;
                break;
            }
        }
        spinner.setSelection(p);
        editName.setText(cat.getName());
        editPrice.setText(cat.getPrice()+"");
        editInfo.setText(cat.getDescribe());

    }
}
