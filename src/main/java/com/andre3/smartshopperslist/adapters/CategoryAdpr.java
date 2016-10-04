package com.andre3.smartshopperslist.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.models.CategoryMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/2/16.
 */

public class CategoryAdpr extends BaseAdapter {

    Context context;
    ArrayList<CategoryMdl> catObj;

    public CategoryAdpr(Context context, ArrayList<CategoryMdl> catObj) {
        this.context = context;
        this.catObj = catObj;
    }

    @Override
    public int getCount() {
        return catObj.size();
    }

    @Override
    public Object getItem(int position) {
        return catObj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.cat_adpr_view, null);
        TextView cat_nm = (TextView)v.findViewById(R.id.cat_title);
        cat_nm.setText(catObj.get(position).getName());

        return v;
    }
}
