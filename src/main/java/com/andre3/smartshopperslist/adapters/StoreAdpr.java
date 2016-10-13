package com.andre3.smartshopperslist.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.models.StoreMdl;
import com.andre3.smartshopperslist.views.PopupBuilder;

import java.util.List;

/**
 * Created by andre3 on 10/2/16.
 */

public class StoreAdpr extends BaseAdapter{

    private List<StoreMdl> storeObj;
    private Context context;

    public StoreAdpr(Context context, List<StoreMdl> storeObj) {
        this.context = context;
        this.storeObj = storeObj;
    }

    @Override
    public int getCount() {

        int arrSize = storeObj.size();
        return arrSize;
    }

    @Override
    public Object getItem(int position) {
        return storeObj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.store_adpr_view, null);
        ImageButton store_edit_btn = (ImageButton)v.findViewById(R.id.store_edit_btn);
        store_edit_btn.setFocusable(false);

        store_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupBuilder dialog1 = new PopupBuilder(context, "Update Store", "store");
                dialog1.displyStoreForm(true, storeObj.get(position).getStoreId()).show();
            }
        });

        TextView store_nm = (TextView)v.findViewById(R.id.lblListItem);
        store_nm.setText(storeObj.get(position).getStoreName());

        return v;
    }
}
