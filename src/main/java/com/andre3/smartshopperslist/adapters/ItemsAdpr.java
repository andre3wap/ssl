package com.andre3.smartshopperslist.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.models.AddItemMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/2/16.
 */

public class ItemsAdpr extends BaseAdapter {

    Context context;
    ArrayList<AddItemMdl> itemsObj;

    public ItemsAdpr(Context context, ArrayList<AddItemMdl> itemsObj) {
        this.context = context;
        this.itemsObj = itemsObj;
    }

    @Override
    public int getCount() {
        return itemsObj.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsObj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.items_adpr_view, null);
        TextView item_nm = (TextView)v.findViewById(R.id.items_title);
        item_nm.setText(itemsObj.get(position).getName());

        return v;
    }
}
