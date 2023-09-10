package com.example.list_view_tp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemModel> arrayList;
    public CustomAdapter(Context context,ArrayList<ItemModel> arrayList) {
        this.context = context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_list,viewGroup,false);
        }
        TextView name,job,email,phone;
        name=view.findViewById(R.id.txt_name);
        job=view.findViewById(R.id.txt_job);
        email=view.findViewById(R.id.txt_email);
        phone=view.findViewById(R.id.txt_phone);

        name.setText(arrayList.get(i).getName());
        job.setText(arrayList.get(i).getJob());
        email.setText(arrayList.get(i).getEmail());
        phone.setText(arrayList.get(i).getPhone());

        final ItemModel temp=arrayList.get(i);
        ImageButton btnCall=view.findViewById(R.id.btn_call);
        btnCall.setOnClickListener(view1 -> {
            Uri telephone = Uri.parse("tel:" + temp.getPhone());
            Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
            context.startActivity(secondeActivite);
        });

        return view;
    }
}
