package com.andre3.smartshopperslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.ItemsAdpr;
import com.andre3.smartshopperslist.adapters.ListTypeAdpr;
import com.andre3.smartshopperslist.impl.CreateItemImpl;
import com.andre3.smartshopperslist.models.AddItemMdl;

/**
 * Created by andre3 on 9/29/16.
 */
public class AddListItem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AddItemMdl data = new  AddItemMdl( 0, "name", 9, "isle", (float)5.9, 4, (float)12.5, 0, 8, 6);
        CreateItemImpl db = new CreateItemImpl(getContext(), data);
        ///db.save();
        View view = inflater.inflate(R.layout.list_items, container, false);

        ListView lv = (ListView) view.findViewById(R.id.ListV);

        ItemsAdpr adapter = new ItemsAdpr(getContext(), db.readData());

        lv.setAdapter(adapter);

        return view;
    }
}
