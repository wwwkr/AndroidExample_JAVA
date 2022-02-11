package com.passcombine.androidid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ListAdapter extends BaseAdapter {

    ArrayList<String> arr;

    public ListAdapter(ArrayList<String> arr) {
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        TextView tv_name = itemView.findViewById(R.id.tv_name);
        TextView tv_package_name = itemView.findViewById(R.id.tv_package_name);
        TextView tv_activity_name = itemView.findViewById(R.id.tv_activity_name);


        tv_name.setText(arr.get(position).split("@")[0]);
        tv_package_name.setText(arr.get(position).split("@")[1]);
        tv_activity_name.setText(arr.get(position).split("@")[2]);

        return itemView;
    }
}
