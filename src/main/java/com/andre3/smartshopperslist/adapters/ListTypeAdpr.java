package com.andre3.smartshopperslist.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.models.CategoryMdl;
import com.andre3.smartshopperslist.models.ListTypeMdl;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/2/16.
 */


public class ListTypeAdpr extends BaseAdapter{

    Context context;
    ArrayList<ListTypeMdl> listtypeObj;

    public ListTypeAdpr(Context context, ArrayList<ListTypeMdl> listtypeObj) {
        this.context = context;
        this.listtypeObj = listtypeObj;
    }

    @Override
    public int getCount() {
        return listtypeObj.size();
    }

    @Override
    public Object getItem(int position) {
        return listtypeObj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ///View v = View.inflate(context, R.layout.listtype_adpr_view, null);
        //TextView type_nm = (TextView)v.findViewById(R.id.type_title);
        ///type_nm.setText(listtypeObj.get(position).getName());

        return null;
    }
}
