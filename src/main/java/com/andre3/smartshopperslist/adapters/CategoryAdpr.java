package com.andre3.smartshopperslist.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.models.CategoryMdl;
import com.andre3.smartshopperslist.views.PopupBuilder;

import java.util.ArrayList;

/**
 * Created by andre3 on 10/2/16.
 */

public class CategoryAdpr extends BaseAdapter {

    Context context;
    ArrayList<CategoryMdl> catObj;
    CategoryAdpr adpr;

    public CategoryAdpr(Context context, ArrayList<CategoryMdl> catObj) {
        this.context = context;
        this.catObj = catObj;
        this.adpr = this;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.cat_adpr_view, null);
        TextView cat_nm = (TextView)v.findViewById(R.id.cat_title);

        ImageButton cat_edit_btn = (ImageButton)v.findViewById(R.id.cat_edit_btn);
        cat_edit_btn.setFocusable(false);

        cat_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PopupBuilder dialog1 = new PopupBuilder(context, "Update Category", "cat");
                dialog1.displyCatForm(true, catObj.get(position).getCatId()).show();
            }
        });

        cat_nm.setText(catObj.get(position).getName());

        return v;
    }
}
